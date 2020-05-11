package com.peait.carmanagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.carmanagesystem.entity.CarInfo;
import com.peait.carmanagesystem.entity.vo.RegistCarInfoVO;
import com.peait.carmanagesystem.exception.GlobalException;
import com.peait.carmanagesystem.mapper.CarInfoMapper;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.CarInfoService;
import com.peait.carmanagesystem.util.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 车辆信息逻辑实现类
 */
@Service
public class CarInfoServiceImpl implements CarInfoService {
    @Resource
    private CarInfoMapper carInfoMapper;
    @Override
    public Result insert(RegistCarInfoVO registCarInfoVO) throws GlobalException {
        //配置属性
        CarInfo carInfo = new CarInfo();
        BeanUtils.copyProperties(registCarInfoVO,carInfo);
        carInfo.setCarcode(UUIDUtil.getSixNum());
        carInfo.setId(UUIDUtil.uuid());
        carInfo.setInsertime(new Date());
        carInfo.setUpdatetime(new Date());
        carInfoMapper.insertSelective(carInfo);
        return Result.success("新增成功");
    }

    /**
     * 修改车辆信息
     * @param registCarInfoVO
     * @return
     */
    @Override
    public Result update(RegistCarInfoVO registCarInfoVO) throws GlobalException {
        //配置属性
        CarInfo carInfo = new CarInfo();
        BeanUtils.copyProperties(registCarInfoVO,carInfo);
        carInfo.setCarcode(UUIDUtil.getSixNum());
        carInfo.setUpdatetime(new Date());
        carInfoMapper.updateByPrimaryKeySelective(carInfo);
        return Result.success("修改成功");
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @Override
    public Result delete(String id)  throws GlobalException {
        carInfoMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功");
    }

    /**
     * 查询车辆信息列表
     * @param carUserName
     * @param page
     * @param limit
     * @return
     */
    @Override
    public TableResult queryList(String carUserName, int page, int limit)  throws GlobalException {
        PageHelper.startPage(page,limit);
        List<CarInfo>  carInfos = carInfoMapper.selectByCarUserName(carUserName);
        PageInfo<CarInfo> carInfoPageInfo = new PageInfo<>(carInfos);

        return new TableResult(carInfoPageInfo.getTotal(),carInfoPageInfo.getList());
    }

    /**
     * 通过id查询车辆
     * @param id
     * @return
     */
    @Override
    public Result queryById(String id)  throws GlobalException {
        CarInfo carInfo = carInfoMapper.selectByPrimaryKey(id);
        return Result.success(carInfo);
    }
}
