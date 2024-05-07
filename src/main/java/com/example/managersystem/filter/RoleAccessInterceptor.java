package com.example.managersystem.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.managersystem.dto.User;
import com.example.managersystem.emun.RoleEnum;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author conglingyan
 * @Date 2024/5/6 16:27
 * @Version 1.0
 * @Description 描述：角色鉴权拦截器
 */
public class RoleAccessInterceptor implements HandlerInterceptor {

    private static final Base64.Decoder DECODE_64 = Base64.getDecoder();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("======================> 拦截器前置处理！");

        String base64 = request.getHeader("base64");
        // 解码
        String deCode = new String(DECODE_64.decode(base64), StandardCharsets.UTF_8);

        System.out.println("deCode = " + deCode);

        JSONObject jsonObject = JSONObject.parseObject(deCode);
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        System.out.println("user.toString() => " + user.toString());

        request.setAttribute("userId", user.getUserId());
        request.setAttribute("role", user.getRole());

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
