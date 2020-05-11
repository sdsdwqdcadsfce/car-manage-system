package com.peait.carmanagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.carmanagesystem.entity.StockManange;
import com.peait.carmanagesystem.exception.GlobalException;
import com.peait.carmanagesystem.mapper.StockManangeMapper;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.StockManangeService;
import com.peait.carmanagesystem.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 库存管理实现类
 */
@Service
public class StockManangeServiceImpl implements StockManangeService {
    @Resource
    private StockManangeMapper stockManangeMapper;
    /**
     * 库存新增
     * @param stockManange
     * @return
     */
    @Override
    public Result insert(StockManange stockManange) throws GlobalException {
        stockManange.setId(UUIDUtil.uuid());
        stockManange.setGoodsid(UUIDUtil.getSixNum());
        stockManange.setInsertime(new Date());
        stockManange.setUpdatetime(new Date());
        stockManangeMapper.insertSelective(stockManange);
        return Result.success("新增成功");
    }

    /**
     * 库存更新
     * @param stockManange
     * @return
     */
    @Override
    public Result update(StockManange stockManange) throws GlobalException {
        stockManange.setGoodsid(null);
        stockManangeMapper.updateByPrimaryKeySelective(stockManange);
        return Result.success("更新成功");
    }

    /**
     * 库存查询
     * @param id
     * @return
     */
    @Override
    public Result query(String  id) throws GlobalException {
        StockManange stockManange = stockManangeMapper.selectByPrimaryKey(id);
        return Result.success(stockManange);
    }

    /**
     * 查询列表
     * @param goodsName
     * @param page
     * @param limit
     * @return
     */
    @Override
    public TableResult queryList(String goodsName, int page, int limit) throws GlobalException {
        PageHelper.startPage(page,limit);
       List<StockManange> StockMananges =  stockManangeMapper.selectByGoodsName(goodsName);
        PageInfo<StockManange> stockManangePageInfo = new PageInfo<>(StockMananges);
        return new TableResult(stockManangePageInfo.getTotal(),stockManangePageInfo.getList());
    }

    /**
     * 库存删除
     * @param id
     * @return
     */
    @Override
    public Result delete(String id)throws GlobalException {
        stockManangeMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功");
    }

}
