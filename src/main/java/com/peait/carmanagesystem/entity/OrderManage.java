package com.peait.carmanagesystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class OrderManage {
    private String id;

    private String ordercode;

    private String ordername;

    private String usercode;

    private String username;

    private String projectcode;

    private String projectname;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date insertime;

    private Date updatetime;


}