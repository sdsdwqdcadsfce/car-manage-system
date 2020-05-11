package com.peait.carmanagesystem.entity.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 车辆信息新增接手实体类
 */
@Data
public class RegistCarInfoVO {
    private String id;
    @NotBlank(message = "车辆颜色不能为空")
    private String carcolor;
    @NotBlank(message = "车辆类型不能为空")
    private String cartype;
    @NotBlank(message = "车辆所属人不能为空")
    private String carusername;
    @NotBlank(message = "车辆型号不能为空")
    private String carmodel;



}
