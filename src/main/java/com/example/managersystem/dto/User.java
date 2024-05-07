package com.example.managersystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author conglingyan
 * @Date 2024/5/6 16:18
 * @Version 1.0
 * @Description 描述：用户类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String accountName;
    /**
     * 用户角色
     */
    private String role;

}
