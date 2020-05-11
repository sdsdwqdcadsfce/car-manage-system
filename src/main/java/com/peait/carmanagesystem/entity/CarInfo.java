package com.peait.carmanagesystem.entity;

import lombok.Data;

import java.util.Date;

/**
 * 汽车信息实体类
 */
@Data
public class CarInfo {
    private String id;

    private String carcode;

    private String carcolor;

    private String cartype;

    private String carusername;

    private String carmodel;

    private Date insertime;

    private Date updatetime;


}