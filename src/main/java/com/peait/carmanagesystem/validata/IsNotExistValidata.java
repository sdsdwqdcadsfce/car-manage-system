package com.peait.carmanagesystem.validata;
import com.peait.carmanagesystem.entity.WorkerInfo;
import com.peait.carmanagesystem.mapper.UserInfoMapper;
import com.peait.carmanagesystem.mapper.WorkerInfoMapper;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义主句 校验是不是存在 校验类
 */
@Component
public class IsNotExistValidata implements ConstraintValidator<IsNotExist, Object> {

    @Resource
    private WorkerInfoMapper workerInfoMapper;

    String tableName;
    String fileName;
    String message;

    @Override
    public void initialize(IsNotExist isNotExist) {
        fileName = isNotExist.fileName();
        tableName = isNotExist.tableName();
        message = isNotExist.message();
    }

    @Override
    public boolean isValid(Object fileValue, ConstraintValidatorContext constraintValidatorContext) {
        int i = workerInfoMapper.IsExistValidata(tableName,fileName,fileValue);
        if(i==0){
            //禁用默认的提示消息
            constraintValidatorContext.disableDefaultConstraintViolation();
            //创建新的提示消息
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }
      return true;
    }
}
