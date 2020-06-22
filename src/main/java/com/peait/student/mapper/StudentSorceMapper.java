package com.peait.student.mapper;

import com.peait.student.entity.StudentSorce;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentSorceMapper {
    int deleteByPrimaryKey(String id);

    int insert(StudentSorce record);

    int insertSelective(StudentSorce record);

    StudentSorce selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StudentSorce record);

    int updateByPrimaryKey(StudentSorce record);

    List<StudentSorce> getList(String subjectName);
}