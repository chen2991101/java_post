package com.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.service.PostService;
import com.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 快递信息的控制器
 * Created by hao on 15/3/27.
 */
@Controller
@RequestMapping(value = "post", produces = Utils.textutf8)
public class PostController {
    @Autowired
    private PostService postService;//快递的服务类
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    @RequestMapping("getPost")
    @ResponseBody
    public String getPostName(@RequestParam String postNum) {
        String postName = postService.request("http://www.kuaidi100.com/autonumber/autoComNum?text=" + postNum);
        JSONArray jsonArray = JSONObject.parseObject(postName).getJSONArray("auto");
        if (jsonArray != null && jsonArray.size() > 0) {
            String simpleName = jsonArray.getJSONObject(0).getString("comCode");
            String url = "http://route.showapi.com/64-19?showapi_appid=" + Utils.appId + "&showapi_timestamp=" + dateFormat.format(new Date()) + "&com=" + simpleName + "&nu=" + postNum + "&showapi_sign=" + Utils.secret;
            return postService.request(url);
        }
        return null;
    }
}
