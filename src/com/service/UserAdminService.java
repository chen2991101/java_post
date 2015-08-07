package com.service;

import com.util.Utils;
import com.alibaba.fastjson.JSONObject;
import com.dao.UserAdminDao;
import com.entity.UserAdmin;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 后台用户登陆的service
 * Created by Administrator on 2015-01-21.
 */
@Service
public class UserAdminService {
    @Autowired
    UserAdminDao userAdminDao;


    /**
     * 后台用户登陆
     *
     * @param userName 用户名
     * @param pwd      密码
     * @return
     */
    public String login(String userName, String pwd, HttpServletRequest request) {
        if (Utils.isEmpty(userName) || Utils.isEmpty(pwd)) {
            return Utils.failure("用户名或密码为空");
        }
        if (userName.length() < 3) {
            return Utils.failure("用户名至少3位");
        }
        if (pwd.length() < 3) {
            return Utils.failure("密码至少3位");
        }

        List list = userAdminDao.findByUserNameAndPwd(userName, DigestUtils.md5Hex(pwd));
        if (list.size() == 0) {
            return Utils.failure("用户名或密码错误");
        } else {
            //登陆成功，保存用户信息到session中
            HttpSession session = request.getSession();
            session.setAttribute("user", list.get(0));
        }
        return Utils.success();
    }


    /**
     * 查询所有的后台管理员
     *
     * @return 后台的所有用户
     */
    public String findAll() {
        return JSONObject.toJSONString(userAdminDao.findAll());
    }

    /**
     * 添加后台管理人员
     *
     * @param userName 用户名
     * @param password 密码
     * @return 返回数据
     */
    public String add(String userName, String password, HttpServletRequest request) {
        if (Utils.isEmpty(userName) || Utils.isEmpty(password)) {
            return Utils.failure("数据不能位空");
        }

        if (userName.length() < 3) {
            return Utils.failure("用户名至少3位");
        }
        if (password.length() < 3) {
            return Utils.failure("密码至少3位");
        }

        List list = userAdminDao.findByUserName(userName);
        if (list.size() > 0) {
            return Utils.failure("用户名已存在");
        }

        //验证完毕，添加用户
        UserAdmin userAdmin = new UserAdmin();
        userAdmin.setUserName(userName);
        userAdmin.setPwd(DigestUtils.md5Hex(password));
        userAdmin.setOperationId(Utils.getUserId(request));
        userAdmin = userAdminDao.save(userAdmin);

        if (!Utils.isEmpty(userAdmin.getId())) {
            return Utils.success();
        } else {
            return Utils.failure("添加失败");
        }
    }

    /**
     * 删除后台用户
     *
     * @param id 需要删除用户的id
     * @return 结果
     */
    @Transactional
    public String delete(String id) {
        if (Utils.isEmpty(id)) {
            return Utils.failure("id不能为空");
        }

        int i = userAdminDao.deleteByIdAndSuper(id, false);
        if (i > 0) {
            return Utils.success();
        }
        return Utils.failure("删除失败");
    }

    /**
     * 修改密码
     *
     * @param id          需要修改的id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param request     请求参数
     * @return
     */
    @Transactional
    public String updatePwd(String id, String oldPassword, String newPassword, HttpServletRequest request) {
        if (Utils.isEmpty(id) || Utils.isEmpty(oldPassword) || Utils.isEmpty(newPassword)) {
            return Utils.failure("数据不能为空");
        }

        if (newPassword.length() < 3 || oldPassword.length() < 3) {
            return Utils.failure("密码至少3位");
        }

        UserAdmin userAdmin = userAdminDao.findOne(id);//需要修改的用户的信息
        if (userAdmin == null) {
            return Utils.failure("需要修改的用户不存在");
        }

        if (!userAdmin.getPwd().equals(DigestUtils.md5Hex(oldPassword))) {
            return Utils.failure("原密码不正确");
        }

        userAdmin.setPwd(DigestUtils.md5Hex(newPassword));
        userAdmin.setOperationId(Utils.getUserId(request));
        return Utils.success();
    }
}
