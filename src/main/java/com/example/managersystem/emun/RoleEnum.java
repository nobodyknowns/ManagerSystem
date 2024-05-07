package com.example.managersystem.emun;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author conglingyan
 * @Date 2024/5/6 16:19
 * @Version 1.0
 * @Description 描述：角色类型枚举类
 */
@AllArgsConstructor
@NoArgsConstructor
public enum RoleEnum {

    ADMIN("1001","admin"),
    USER("1002","user");

    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型描述
     */
    private String value;

}
