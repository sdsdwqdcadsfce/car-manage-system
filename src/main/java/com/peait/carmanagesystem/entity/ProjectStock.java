package com.peait.carmanagesystem.entity;

import lombok.Data;

@Data
public class ProjectStock {
    private String id;

    private String projectmanageid;

    private String stockmanageid;

    private Integer goodscount;

}