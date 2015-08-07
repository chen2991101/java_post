package com.service;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;

/**
 * 快递的service
 * Created by Administrator on 2015-01-21.
 */
@Service
public class PostService {


    /**
     * 发送请求
     *
     * @param url 取药请求的url
     * @return
     */
    public String request(String url) {
        try {
            URL u = new URL(url);
            InputStream in = u.openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                byte buf[] = new byte[1024];
                int read = 0;
                while ((read = in.read(buf)) > 0) {
                    out.write(buf, 0, read);
                }
            } finally {
                if (in != null) {
                    in.close();
                }
            }
            byte b[] = out.toByteArray();
            return new String(b, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
