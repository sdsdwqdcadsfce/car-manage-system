package com.peait.carmanagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.carmanagesystem.entity.OrderManage;
import com.peait.carmanagesystem.entity.ProjectBeautyManage;
import com.peait.carmanagesystem.entity.UserInfo;
import com.peait.carmanagesystem.exception.GlobalException;
import com.peait.carmanagesystem.mapper.OrderManageMapper;
import com.peait.carmanagesystem.mapper.ProjectBeautyManageMapper;
import com.peait.carmanagesystem.mapper.UserInfoMapper;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.OrderManageService;
import com.peait.carmanagesystem.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrderManageServiceImpl  implements OrderManageService {
    @Resource
    private OrderManageMapper orderManageMapper;

    @Resource
    private ProjectBeautyManageMapper projectBeautyManageMapper;

    @Resource
    private UserInfoMapper userInfoMapper;
    /**
     * 订单新增
     * @param orderManage
     * @return
     */
    @Override
    public Result insert(OrderManage orderManage) throws GlobalException {
        orderManage.setId(UUIDUtil.uuid());
        orderManage.setInsertime(new Date());
        orderManage.setUpdatetime(new Date());
        orderManage.setOrdercode(UUIDUtil.getSixNum());
        orderManageMapper.insertSelective(orderManage);
        return Result.success("新增成功");
    }

    /**
     * 更新订单
     * @param orderManage
     * @return
     */
    @Override
    public Result update(OrderManage orderManage) {
        int i = orderManageMapper.updateByPrimaryKeySelective(orderManage);
        return Result.success("更新成功");
    }

    /**
     * 查询订单
     * @param id
     * @return
     */
    @Override
    public Result query(String id) {
        OrderManage orderManage = orderManageMapper.selectByPrimaryKey(id);
        return Result.success(orderManage);
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @Override
    public Result delete(String id) {
        int i = orderManageMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功");
    }

    /**
     * 查询订单列表
     * @param ordercode
     * @param page
     * @param limit
     * @return
     */
    @Override
    public TableResult queryList(String ordercode, int page, int limit) {
        PageHelper.startPage(page,limit);
        List<OrderManage> orderManageList = orderManageMapper.queryList(ordercode);
        PageInfo<OrderManage> orderManagePageInfo = new PageInfo<>(orderManageList);
        return new TableResult(orderManagePageInfo.getTotal(),orderManagePageInfo.getList());
    }

    /**
     * 前端订单新增
     * @param orderManage
     * @return
     */
    @Override
    public Result webInsert(OrderManage orderManage) {
        //查询项目的信息
        ProjectBeautyManage projectBeautyManage = projectBeautyManageMapper.selectByPrimaryKey(orderManage.getId());
        UserInfo userInfo = userInfoMapper.selectByUserName(orderManage.getUsername());
        orderManage.setId(UUIDUtil.uuid());
        orderManage.setInsertime(new Date());
        orderManage.setUpdatetime(new Date());
        orderManage.setOrdercode(UUIDUtil.getSixNum());
        orderManage.setOrdername("前端订单");
        orderManage.setProjectcode(projectBeautyManage.getProjectcode());
        orderManage.setProjectname(projectBeautyManage.getProjectname());
        orderManage.setUsercode(userInfo.getUsercode());
        orderManage.setUsername(userInfo.getRealname());
        orderManageMapper.insertSelective(orderManage);
        return Result.success("新增成功");
    }

    /**
     * 查询我的订单
     * @param userName
     * @return
     */
    @Override
    public TableResult queryByUserName(String userName) {
        UserInfo userInfo = userInfoMapper.selectByUserName(userName);
        List<OrderManage> orderList = orderManageMapper.queryListByUserCode(userInfo.getUsercode());
        TableResult<OrderManage> tTableResult = new TableResult<OrderManage>(Long.parseLong(String.valueOf(orderList.size())),orderList);
        return tTableResult;
    }
}
