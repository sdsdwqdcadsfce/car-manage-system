package com.peait.student.mapper;

import com.peait.student.entity.Testinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestinfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(Testinfo record);

    int insertSelective(Testinfo record);

    Testinfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Testinfo record);

    int updateByPrimaryKey(Testinfo record);

    List<Testinfo> getList(String subjectName);
}