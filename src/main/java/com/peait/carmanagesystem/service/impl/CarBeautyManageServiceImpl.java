package com.peait.carmanagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.carmanagesystem.entity.CarBeautyManage;
import com.peait.carmanagesystem.entity.Goods;
import com.peait.carmanagesystem.entity.StockManange;
import com.peait.carmanagesystem.entity.vo.CarBeautyManageVO;
import com.peait.carmanagesystem.exception.GlobalException;
import com.peait.carmanagesystem.mapper.CarBeautyManageMapper;
import com.peait.carmanagesystem.mapper.ProjectStockMapper;
import com.peait.carmanagesystem.mapper.StockManangeMapper;
import com.peait.carmanagesystem.result.CodeMsg;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.CarBeautyManageService;
import com.peait.carmanagesystem.util.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CarBeautyManageServiceImpl implements CarBeautyManageService {
    @Resource
    private CarBeautyManageMapper carBeautyManageMapper;
    @Resource
    private ProjectStockMapper projectStockMapper;
    @Resource
    private StockManangeMapper stockManangeMapper;
    /**
     * 新增车辆美容项目
     * @param carBeautyManageVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result insert(CarBeautyManageVO carBeautyManageVO)throws GlobalException {
        carBeautyManageVO.setId(UUIDUtil.uuid());
        carBeautyManageVO.setInsertime(new Date());
        carBeautyManageVO.setUpdatetime(new Date());
        CarBeautyManage carBeautyManage = new CarBeautyManage();
        BeanUtils.copyProperties(carBeautyManageVO,carBeautyManage);
        carBeautyManageMapper.insert(carBeautyManage);
        //新增项目后扣除库存
        //查询这个项目的所有商品和数量 对应修改成新的数量
        List<Goods> goods = projectStockMapper.selectByProjectId(carBeautyManageVO.getCarbeautyid());
        if(!goods.isEmpty()){
            for (Goods good:goods) {
                StockManange stockManange = stockManangeMapper.selectByPrimaryKey(good.getId());
                Integer goodscount = stockManange.getGoodscount();
                int newGoodCount = goodscount-good.getCount();
                stockManange.setGoodscount(newGoodCount);
                if(newGoodCount<0){
                    return Result.error(CodeMsg.LESS_THAN_ZERO);
                }
                stockManangeMapper.updateByPrimaryKeySelective(stockManange);
            }
        }
        return Result.success("新增成功");
    }

    @Override
    public Result update(CarBeautyManageVO carBeautyManageVO) throws GlobalException {
        CarBeautyManage carBeautyManage = new CarBeautyManage();
        BeanUtils.copyProperties(carBeautyManageVO,carBeautyManage);
        carBeautyManageMapper.updateByPrimaryKeySelective(carBeautyManage);
        return Result.success("修改成功");
    }

    @Override
    public Result delete(String id) throws GlobalException {
        carBeautyManageMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功");
    }

    @Override
    public Result query(String id)throws GlobalException {
        CarBeautyManage carBeautyManage = carBeautyManageMapper.selectByPrimaryKey(id);
        return  Result.success(carBeautyManage);
    }

    @Override
    public TableResult queryList(String carCode, int page, int limit) throws GlobalException {
        PageHelper.startPage(page,limit);
        List<CarBeautyManage> carBeautyManages = carBeautyManageMapper.selectByCarcode(carCode);
        PageInfo<CarBeautyManage> carBeautyManagePageInfo = new PageInfo<>(carBeautyManages);
        return new TableResult(carBeautyManagePageInfo.getTotal(),carBeautyManagePageInfo.getList());
    }
}
