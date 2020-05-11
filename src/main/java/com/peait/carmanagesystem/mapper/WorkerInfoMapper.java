package com.peait.carmanagesystem.mapper;

import com.peait.carmanagesystem.entity.UserInfo;
import com.peait.carmanagesystem.entity.WorkerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkerInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(WorkerInfo record);

    int insertSelective(WorkerInfo record);

    WorkerInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkerInfo record);

    int updateByPrimaryKey(WorkerInfo record);

    int IsExistValidataNotId(@Param("tableName") String tableName, @Param("fileName") String fileName, @Param("fileValue") Object fileValue,@Param("id")String id);
    //判断是不是重复
    int IsExistValidata(@Param("tableName") String tableName, @Param("fileName") String fileName, @Param("fileValue") Object fileValue);


    /**
     * 查询员工列表
     * @param realName
     * @return
     */
    List<WorkerInfo> getWorkList(@Param("realName") String realName);

    WorkerInfo selectUserByWorkerName(String userName);
}