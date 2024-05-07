package com.example.managersystem.as;

import com.example.managersystem.dto.UserBo;
import com.example.managersystem.emun.RoleEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author conglingyan
 * @Date 2024/5/7 16:27
 * @Version 1.0
 * @Description 描述：服务类
 */
@Service
public class UserService {

    /**
     * 增加用户
     * @param userBo
     * @param role
     * @return
     */
    public ResponseEntity addUser( UserBo userBo, String role) {
        if (RoleEnum.ADMIN.name().equals(role)) {
            System.out.println("执行了 addUser() " + userBo.toString());
            String str = userBo + ";";
            FileOutputStream fos;
            try {
                fos = new FileOutputStream("userFile.txt",true);
                fos.write(str.getBytes());
                fos.close();
            } catch (IOException fe) {
                return new ResponseEntity("文件异常", HttpStatus.OK);
            }
            return new ResponseEntity("success", HttpStatus.OK);
        } else {
            return new ResponseEntity("no authority", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    /**
     * 获取资源
     * @param resource
     * @param userId
     * @return
     */
    public ResponseEntity getResource(String resource, String userId) {
        FileInputStream fis;
        try {
            fis = new FileInputStream("userFile.txt");
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String fileContent = new String(buffer, "UTF-8"); // 假设文件编码为UTF-8
            System.out.println("fileContent => " + fileContent);
            String[] split = fileContent.split(";");
            for (String str : split) {
                System.out.println("str => "  + str);
                if (str.contains("userId") && str.contains("endpoint=")) {
                    String[] split1 = str.split("userId=");
                    if (split1.length > 1) {
                        String[] split2 = split1[1].split(",");
                        if (Objects.equals(userId, split2[0])) {
                            String[] split3 = str.split("endpoint=");
                            if (split3.length > 1) {
                                int i = split3[1].indexOf("[");
                                int j = split3[1].indexOf("]");
                                String substring = split3[1].substring(i + 1, j);
                                System.out.println("substring => " + substring);
                                String[] split4 = substring.split(",");
                                System.out.println("resource.trim() => " + resource.trim());
                                for (String s : split4) {
                                    System.out.println("s.trim() => " + s.trim());
                                    if (s.trim().equals(resource.trim())) {
                                        return new ResponseEntity("success", HttpStatus.OK);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            return new ResponseEntity("文件异常", HttpStatus.OK);
        }
        return new ResponseEntity("no authority", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
}
