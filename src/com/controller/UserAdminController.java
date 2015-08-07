package com.controller;

import com.util.Utils;
import com.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台管理登陆
 * Created by hao on 15/3/27.
 */
@Controller
@RequestMapping(value = "user", produces = Utils.textutf8)
public class UserAdminController {
    @Autowired
    UserAdminService userAdminService;//后台用户登陆的service


    @RequestMapping("findAll")
    @ResponseBody
    public String findAll() {
        return userAdminService.findAll();
    }

    /**
     * 添加后台用户
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public String add(String userName, String password, HttpServletRequest request) {
        return userAdminService.add(userName, password, request);
    }

    /**
     * 删除后台用户
     *
     * @param id 需要删除的id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delete(String id) {
        return userAdminService.delete(id);
    }

    /**
     * 修改用户密码
     *
     * @param id          需要修改的用户的id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 返回数据
     */
    @RequestMapping("updatePwd")
    @ResponseBody
    public String updatePwd(String id, String oldPassword, String newPassword, HttpServletRequest request) {
        return userAdminService.updatePwd(id, oldPassword, newPassword, request);
    }
}
