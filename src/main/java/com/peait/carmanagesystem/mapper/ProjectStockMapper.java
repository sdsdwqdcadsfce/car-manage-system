package com.peait.carmanagesystem.mapper;

import com.peait.carmanagesystem.entity.Goods;
import com.peait.carmanagesystem.entity.ProjectStock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectStockMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProjectStock record);

    int insertSelective(ProjectStock record);

    ProjectStock selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProjectStock record);

    int updateByPrimaryKey(ProjectStock record);

    List<Goods> selectByProjectId(String id);

    void deleteByProjctId(String id);
}