package com.peait.carmanagesystem.mapper;

import com.peait.carmanagesystem.entity.CarBeautyManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarBeautyManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(CarBeautyManage record);

    int insertSelective(CarBeautyManage record);

    CarBeautyManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CarBeautyManage record);

    int updateByPrimaryKey(CarBeautyManage record);

    List<CarBeautyManage> selectByCarcode(@Param("carCode") String carCode);
}