package com.peait.carmanagesystem.entity;

import lombok.Data;

import java.util.Date;
@Data
public class ProjectBeautyManage {
    private String id;

    private String projectid;

    private String projectcode;

    private String projectname;

    private String projectunit;

    private String projectmoney;

    private Date insertime;

    private Date updatetime;

    private String goodsid;


}