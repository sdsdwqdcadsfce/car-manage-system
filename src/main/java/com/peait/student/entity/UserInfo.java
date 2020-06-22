package com.peait.student.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户实体类
 */
@Data
public class UserInfo {

    private String id;

    private String usercode;

    private String username;
//    @JsonIgnore
    private String password;

    private String realname;

    private String useraddress;

    private String userphone;

    private String userremarks;

    private Date insertime;

    private Date updatetime;


}