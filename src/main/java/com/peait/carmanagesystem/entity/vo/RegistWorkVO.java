package com.peait.carmanagesystem.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peait.carmanagesystem.validata.IsExist;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户注册接手实体类
 */
@Data
public class RegistWorkVO {
    private String workercode;
    @NotBlank(message = "用户名不能为空")
    @IsExist(tableName = "worker_info",fileName = "workerName",message = "账号已存在")
    private String workername;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "员工姓名不能为空")
    private String realname;
    @NotBlank(message = "员工地址不能为空")
    private String workeraddress;
    @NotBlank(message = "用户联系方式不能为空")
    @IsExist(tableName = "worker_info",fileName = "workerPhone",message = "手机号已存在")
    private String workerphone;
    private String workerremarks;
    private String workerAuthority;

}
