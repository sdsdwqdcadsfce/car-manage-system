package com.peait.carmanagesystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 库存管理实体类
 */
@Data
public class StockManange {
    private String id;
    private String goodsid;
    @NotBlank(message = "商品名称不能为空")
    private String goodsname;
    @NotNull(message = "进货时间不能为空")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date goodsdate;
    @NotNull(message = "商品数量不能为空")
    private Integer goodscount;
    private String suppliername;
    @NotBlank(message = "商品价格不能为空")
    private String goodsmoney;

    private Date insertime;

    private Date updatetime;


}