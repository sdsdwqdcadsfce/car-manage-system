package com.peait.student.mapper;

import com.peait.student.entity.Books;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BooksMapper {
    int deleteByPrimaryKey(String id);

    int insert(Books record);

    int insertSelective(Books record);

    Books selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKey(Books record);

    List<Books> getList(String bookName);
}