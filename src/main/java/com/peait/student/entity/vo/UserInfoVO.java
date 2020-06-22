package com.peait.student.entity.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户实体类
 */
@Data
public class UserInfoVO {
    @NotBlank(message = "修改id不能为空")
    private String id;

//    private String usercode;

    private String username;
//    @JsonIgnore
    private String password;

    private String realname;

    private String useraddress;

    private String userphone;

    private String userremarks;

//    private Date insertime;
//
//    private Date updatetime;


}