package com.peait.carmanagesystem.mapper;

import com.peait.carmanagesystem.entity.StockManange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockManangeMapper {
    int deleteByPrimaryKey(String id);

    int insert(StockManange record);

    int insertSelective(StockManange record);

    StockManange selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective( StockManange record);

    int updateByPrimaryKey(StockManange record);

    List<StockManange> selectByGoodsName(@Param("goodsName") String goodsName);
}