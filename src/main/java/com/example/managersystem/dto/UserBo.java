package com.example.managersystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author conglingyan
 * @Date 2024/5/7 12:24
 * @Version 1.0
 * @Description 描述：页面请求用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBo implements Serializable {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 允许访问的资源
     */
    private List<String> endpoint;
}
