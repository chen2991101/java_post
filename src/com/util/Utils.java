package com.util;

import com.alibaba.fastjson.JSONObject;
import com.entity.UserAdmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 工具类方法
 * Created by Administrator on 2015-01-21.
 */
public class Utils {
    public static final String textutf8 = "text/html;charset=UTF-8";
    public static String secret = "72d51f402d39481b948f0658d34afce8";//查询api的密码
    public static int appId = 5575;//查询快递的appId


    /**
     * 返回成功
     *
     * @param obj
     * @return
     */
    public static String success(Object obj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("obj", obj);
        return jsonObject.toJSONString();
    }

    /**
     * 返回成功
     *
     * @return
     */
    public static String success() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        return jsonObject.toJSONString();
    }

    /**
     * 成功返回用户的token
     *
     * @param token 需要返回的token
     * @return 需要返回的信息
     */
    public static String success(String token) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("token", token);
        return jsonObject.toJSONString();
    }


    /**
     * 返回失败信息
     *
     * @param msg
     * @return
     */
    public static String failure(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", false);
        jsonObject.put("msg", msg);
        return jsonObject.toJSONString();
    }


    /**
     * 返回当前操作人的id
     *
     * @param request
     * @return
     */
    public static String getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserAdmin user = (UserAdmin) session.getAttribute("user");
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    /**
     * 验证文本是否为空
     *
     * @param str 需要验证的文本
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || str.length() == 0 ? true : false;
    }
}
