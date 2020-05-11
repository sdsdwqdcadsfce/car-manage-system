package com.peait.carmanagesystem.mapper;

import com.peait.carmanagesystem.entity.ServiceList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceListMapper {
    int insert(ServiceList record);

    int insertSelective(ServiceList record);

    List<ServiceList> query();
}