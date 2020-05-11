package com.peait.carmanagesystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
@Data
public class WorkerInfo {
    private String id;

    private String workercode;

    private String workername;

//    @JsonIgnore
    private String password;

    private String realname;

    private String workeraddress;

    private String workerphone;

    private String workerremarks;

    private Date insertime;

    private Date updatetime;



}