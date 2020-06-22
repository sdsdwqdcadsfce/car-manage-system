package com.peait.student.entity.vo;

import com.peait.student.validata.IsExist;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户注册接手实体类
 */
@Data
public class RegistUserVO {
    private String usercode;
    @NotBlank(message = "用户名不能为空")
    @IsExist(tableName = "user_info",fileName = "userName",message = "账号已存在")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "用户姓名不能为空")
    private String realname;
    @NotBlank(message = "用户地址不能为空")
    private String useraddress;
    @NotBlank(message = "用户联系方式不能为空")
    @IsExist(tableName = "user_info",fileName = "userPhone",message = "手机号已存在")
    private String userphone;
    private String userremarks;
}
