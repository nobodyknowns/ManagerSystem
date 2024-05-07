package com.example.managersystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.managersystem.dto.User;
import com.example.managersystem.dto.UserBo;
import com.example.managersystem.emun.RoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Author conglingyan
 * @Date 2024/5/6 18:22
 * @Version 1.0
 * @Description 描述：todo ...
 */
class TestControllerTest {

    private static final Base64.Encoder ENCODE_64 = Base64.getEncoder();
    private  final  static String url =  "http://localhost:8080/";
    private static RestTemplate restTemplate = new RestTemplate();

    @Test
    public void roleTest() {

        User user = User.builder().userId(123458).accountName("甲方").role(RoleEnum.ADMIN.name()).build();
//        User user = User.builder().userId(123456).accountName("乙方").role(RoleEnum.USER.name()).build();
        String jsonString = JSONObject.toJSONString(user);
        String encodedToStr = ENCODE_64.encodeToString(jsonString.getBytes(StandardCharsets.UTF_8));
        // eyJhY2NvdW50TmFtZSI6IueUsuaWuSIsInJvbGUiOiJBRE1JTiIsInVzZXJJZCI6MTIzNDU2fQ==
        // eyJhY2NvdW50TmFtZSI6IuS5meaWuSIsInJvbGUiOiJVU0VSIiwidXNlcklkIjoxMjM0NTZ9
        System.out.println("encodedToStr = " + encodedToStr);

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json;charset=UTF-8");
        headers.add("base64", encodedToStr);

        List<String> list = new ArrayList<>();
        list.add("resource A");
        list.add("resource B");
        list.add("resource C");
        UserBo userBo = UserBo.builder().userId(123456).endpoint(list).build();
        String entity = JSONObject.toJSONString(userBo);

        ResponseEntity<String> response = restTemplate.exchange(url + "manager/web/admin/addUser",
                HttpMethod.POST,
                new HttpEntity(entity, headers),
                String.class);
        System.out.println("result: " + response.getBody());

//        ResponseEntity<String> response = restTemplate.exchange(url + "manager/web/user/resource C",
//                HttpMethod.GET,
//                new HttpEntity(headers),
//                String.class);
//        System.out.println("result: " + response.getBody());
    }
}