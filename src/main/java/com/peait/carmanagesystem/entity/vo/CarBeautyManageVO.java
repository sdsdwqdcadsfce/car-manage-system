package com.peait.carmanagesystem.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CarBeautyManageVO {
    private String id;

    private Date businessDate;

    private String carcode;

    private String carbeautyid;

    private String usedgoods;

    private Date insertime;

    private Date updatetime;

}