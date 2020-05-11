package com.peait.carmanagesystem.entity.vo;

import com.peait.carmanagesystem.entity.Goods;
import com.peait.carmanagesystem.entity.ServiceList;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Data
public class RegistProjectBeautyManageVO {
    private String id ;
    private String projectid;
    @NotBlank(message = "项目名称不能为空")
    private String projectname;

    private String projectunit;
    @NotBlank(message = "项目价格不能为空")
    private String projectmoney;

    private List<Goods> goods;

    private List<ServiceList> services;


}