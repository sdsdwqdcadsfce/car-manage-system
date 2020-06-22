package com.peait.student.mapper;

import com.peait.student.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    int IsExistValidataNotId(@Param("tableName") String tableName, @Param("fileName") String fileName, @Param("fileValue") Object fileValue,@Param("id")String id);
    //判断是不是重复
    int IsExistValidata(@Param("tableName") String tableName, @Param("fileName") String fileName, @Param("fileValue") Object fileValue);

    int deleteByPrimaryKey(String id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> getUserList(@Param("userName") String userName);

    List<UserInfo> queryUser();

    UserInfo selectByUserName(@Param("userName") String userName);
}