package com.peait.carmanagesystem.mapper;

import com.peait.carmanagesystem.entity.CarInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(CarInfo record);

    int insertSelective(CarInfo record);

    CarInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CarInfo record);

    int updateByPrimaryKey(CarInfo record);

    List<CarInfo> selectByCarUserName(@Param("carUserName") String carUserName);
}