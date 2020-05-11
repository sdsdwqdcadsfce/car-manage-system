package com.peait.carmanagesystem.mapper;

import com.peait.carmanagesystem.entity.ProjectService;
import com.peait.carmanagesystem.entity.ServiceList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectServiceMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProjectService record);

    int insertSelective(ProjectService record);

    ProjectService selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProjectService record);

    int updateByPrimaryKey(ProjectService record);

    List<ServiceList> selectByProjectId(String id);

    void deleteByProjctId(String id);
}