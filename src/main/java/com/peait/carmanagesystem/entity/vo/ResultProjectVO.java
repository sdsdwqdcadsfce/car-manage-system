package com.peait.carmanagesystem.entity.vo;

import com.peait.carmanagesystem.entity.Goods;
import com.peait.carmanagesystem.entity.ServiceList;
import lombok.Data;

import java.util.List;

@Data
public class ResultProjectVO {

    private String id;

    private String projectid;

    private String projectcode;

    private String projectname;

    private String projectunit;

    private String projectmoney;

    //产品的信息
    private List<Goods> goods;
    //服务的信息
    private List<ServiceList> services;
}
