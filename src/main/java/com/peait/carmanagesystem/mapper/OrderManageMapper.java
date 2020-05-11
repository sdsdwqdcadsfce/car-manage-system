package com.peait.carmanagesystem.mapper;

import com.peait.carmanagesystem.entity.OrderManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderManage record);

    int insertSelective(OrderManage record);

    OrderManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderManage record);

    int updateByPrimaryKey(OrderManage record);

    List<OrderManage> queryList(@Param("ordercode") String ordercode);

    List<OrderManage> queryListByUserCode(@Param("usercode") String usercode);
}