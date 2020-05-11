package com.peait.carmanagesystem.entity;

import lombok.Data;

import java.util.Date;
@Data
public class AuthoritySet {
    private String id;

    private String workercode;

    private String workername;

    private String workerauthority;

    private Date insertime;

    private Date updatetime;


}