package com.peait.carmanagesystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class CarBeautyManage {
    private String id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date businessDate;

    private String carcode;

    private String carbeautyid;

    private String usedgoods;

    private Date insertime;

    private Date updatetime;


}