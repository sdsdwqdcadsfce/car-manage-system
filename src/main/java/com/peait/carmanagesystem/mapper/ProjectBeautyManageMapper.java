package com.peait.carmanagesystem.mapper;

import com.peait.carmanagesystem.entity.ProjectBeautyManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectBeautyManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProjectBeautyManage record);

    int insertSelective(ProjectBeautyManage record);

    ProjectBeautyManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProjectBeautyManage record);

    int updateByPrimaryKey(ProjectBeautyManage record);


    List<ProjectBeautyManage> selectByProjectName(@Param("projectname") String projectname);

    List<ProjectBeautyManage> queryName();
}