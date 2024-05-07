package com.example.managersystem.controller;

import com.example.managersystem.as.UserService;
import com.example.managersystem.dto.UserBo;
import com.example.managersystem.emun.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author conglingyan
 * @Date 2024/5/6 16:15
 * @Version 1.0
 * @Description 描述：Manager System Controller
 */
@Controller
@RequestMapping("manager/web")
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/admin/addUser")
    @ResponseBody
    public ResponseEntity addUser(@RequestBody UserBo userBo, HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        return userService.addUser(userBo, role);
    }

    @RequestMapping("/user/{resource}")
    @ResponseBody
    public ResponseEntity getResource(@PathVariable String resource, HttpServletRequest request) throws IOException {
        System.out.println("执行了 getResource() ，请求的资源是：" + resource);
        String userId = request.getAttribute("userId").toString();
        return userService.getResource(resource, userId);
    }

}
