package com.peait.carmanagesystem.mapper;

import com.peait.carmanagesystem.entity.AuthoritySet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthoritySetMapper {
    int deleteByPrimaryKey(String id);

    int insert(AuthoritySet record);

    int insertSelective(AuthoritySet record);

    AuthoritySet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AuthoritySet record);

    int updateByPrimaryKey(AuthoritySet record);

    void deleteByWorkerCode(String workercode);

    AuthoritySet selectByWorkerCode(@Param("workerCode") String workerCode);

    List<AuthoritySet> selectByWorkerName(@Param("workerName") String workerName);
}