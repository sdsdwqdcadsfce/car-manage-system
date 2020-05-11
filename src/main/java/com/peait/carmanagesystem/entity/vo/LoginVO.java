package com.peait.carmanagesystem.entity.vo;

import com.peait.carmanagesystem.validata.IsNotExist;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginVO {
    @NotNull(message = "用户名不能为空")
    @IsNotExist(tableName = "worker_info",fileName = "workerName",message = "用户名不存在")
    private String userName;
    @NotNull(message = "密码不能为空")
    private String passWord;
}
