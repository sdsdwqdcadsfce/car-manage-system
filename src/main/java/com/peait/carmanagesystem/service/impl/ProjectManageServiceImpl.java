package com.peait.carmanagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.carmanagesystem.entity.*;
import com.peait.carmanagesystem.entity.vo.RegistProjectBeautyManageVO;
import com.peait.carmanagesystem.entity.vo.ResultProjectVO;
import com.peait.carmanagesystem.exception.GlobalException;
import com.peait.carmanagesystem.mapper.ProjectBeautyManageMapper;
import com.peait.carmanagesystem.mapper.ProjectServiceMapper;
import com.peait.carmanagesystem.mapper.ProjectStockMapper;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.ProjectManageService;
import com.peait.carmanagesystem.util.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 美容项目的实现类
 */
@Service
public class ProjectManageServiceImpl implements ProjectManageService {
    @Resource
    private ProjectBeautyManageMapper projectBeautyManageMapper;
    @Resource
    private ProjectServiceMapper projectServiceMapper;
    @Resource
    private ProjectStockMapper projectStockMapper;

    /**
     * 美容项目的新增
     *
     * @param registProjectBeautyManageVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result insert(RegistProjectBeautyManageVO registProjectBeautyManageVO) throws GlobalException {
        //增加美容项目数据
        ProjectBeautyManage projectBeautyManage = new ProjectBeautyManage();
        BeanUtils.copyProperties(registProjectBeautyManageVO, projectBeautyManage);
        String projectId = UUIDUtil.uuid();
        projectBeautyManage.setId(projectId);
        projectBeautyManage.setProjectid(UUIDUtil.getSixNum());
        projectBeautyManage.setInsertime(new Date());
        projectBeautyManage.setProjectcode(UUIDUtil.getSixNum());
        projectBeautyManage.setUpdatetime(new Date());
        projectBeautyManageMapper.insertSelective(projectBeautyManage);
        //新增美容和服务的中间表数据
        //如果新增的服务类不是空的就进行新增
        if (!registProjectBeautyManageVO.getServices().isEmpty()) {
            for (ServiceList serviceList : registProjectBeautyManageVO.getServices()) {
                ProjectService projectService = new ProjectService();
                projectService.setId(UUIDUtil.uuid());
                projectService.setProjectid(projectId);
                projectService.setServicelistcode(serviceList.getServicecode());
                projectServiceMapper.insertSelective(projectService);
            }
        }
        //如果产品类不是空的
        //新增数据到美容项目和产品中间表中
        if (!registProjectBeautyManageVO.getGoods().isEmpty()) {
            for (Goods goods : registProjectBeautyManageVO.getGoods()) {
                ProjectStock projectStock = new ProjectStock();
                projectStock.setId(UUIDUtil.uuid());
                projectStock.setGoodscount(goods.getCount());
                projectStock.setProjectmanageid(projectId);
                projectStock.setStockmanageid(goods.getId());
                projectStockMapper.insertSelective(projectStock);
            }
        }
        return Result.success("新增成功");
    }

    /**
     * 查询美容项目
     *
     * @param id
     * @return
     */
    @Override
    public Result query(String id) throws GlobalException {
        ResultProjectVO resultProjectVO = this.getResultById(id);
        //返回
        return Result.success(resultProjectVO);
    }

    /**
     * 通过id查询信息的公共类
     *
     * @param id
     * @return
     */
    private ResultProjectVO getResultById(String id) {
        //查询美容项目的基本信息
        ProjectBeautyManage projectBeautyManage = projectBeautyManageMapper.selectByPrimaryKey(id);
        //封装一个新的返回实体类
        ResultProjectVO resultProjectVO = new ResultProjectVO();
        //复制美容项目实体中现存的属性
        BeanUtils.copyProperties(projectBeautyManage, resultProjectVO);
        //查询这个项目中的服务的信息
        List<ServiceList> projectServices = projectServiceMapper.selectByProjectId(id);
        resultProjectVO.setServices(projectServices);
        //查询这个项目中的产品的信息
        List<Goods> goods = projectStockMapper.selectByProjectId(id);
        resultProjectVO.setGoods(goods);
        return resultProjectVO;
    }

    /**
     * 修改美容项目
     *
     * @param registProjectBeautyManageVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result update(RegistProjectBeautyManageVO registProjectBeautyManageVO) {
        //修改美容项目
        ProjectBeautyManage projectBeautyManage = new ProjectBeautyManage();
        BeanUtils.copyProperties(registProjectBeautyManageVO, projectBeautyManage);
        projectBeautyManage.setUpdatetime(new Date());
        projectBeautyManageMapper.updateByPrimaryKeySelective(projectBeautyManage);
        //删除美容项目关联的服务和产品
        projectStockMapper.deleteByProjctId(registProjectBeautyManageVO.getId());
        projectServiceMapper.deleteByProjctId(registProjectBeautyManageVO.getId());
        //增加美容项目关联的数据
        //如果新增的服务类不是空的就进行新增
        if (!registProjectBeautyManageVO.getServices().isEmpty()) {
            for (ServiceList serviceList : registProjectBeautyManageVO.getServices()) {
                ProjectService projectService = new ProjectService();
                projectService.setId(UUIDUtil.uuid());
                projectService.setProjectid(registProjectBeautyManageVO.getId());
                projectService.setServicelistcode(serviceList.getServicecode());
                projectServiceMapper.insertSelective(projectService);
            }
        }
        //如果产品类不是空的
        //新增数据到美容项目和产品中间表中
        if (!registProjectBeautyManageVO.getGoods().isEmpty()) {
            for (Goods goods : registProjectBeautyManageVO.getGoods()) {
                ProjectStock projectStock = new ProjectStock();
                projectStock.setId(UUIDUtil.uuid());
                projectStock.setGoodscount(goods.getCount());
                projectStock.setProjectmanageid(registProjectBeautyManageVO.getId());
                projectStock.setStockmanageid(goods.getId());
                projectStockMapper.insertSelective(projectStock);
            }
        }

        //返回
        return Result.success("修改成功");
    }

    /**
     * 删除项目
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result delete(String id) {
        projectBeautyManageMapper.deleteByPrimaryKey(id);
        //删除美容项目关联的服务和产品
        projectStockMapper.deleteByProjctId(id);
        projectServiceMapper.deleteByProjctId(id);
        return Result.success("删除成功");
    }

    /**
     * 查询列表
     *
     * @param projectname
     * @param page
     * @param limit
     * @return
     */
    @Override
    public TableResult queryList(String projectname, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<ProjectBeautyManage> projectBeautyManageList = projectBeautyManageMapper.selectByProjectName(projectname);
        PageInfo<ProjectBeautyManage> projectBeautyManagePageInfo = new PageInfo<>(projectBeautyManageList);
        //设置返回体
        List<ResultProjectVO> resultProjectVOS = new ArrayList<>();
        List<ProjectBeautyManage> list = projectBeautyManagePageInfo.getList();
        for (int i = 0; i < list.size(); i++) {
            resultProjectVOS.add(this.getResultById(list.get(i).getId()));
        }
        //查询
        return new TableResult(projectBeautyManagePageInfo.getTotal(), resultProjectVOS);
    }

    /**
     * 查询项目的名称
     * @return
     */
    @Override
    public Result queryName() {
        List<ProjectBeautyManage>  result= projectBeautyManageMapper.queryName();
        return Result.success(result);
    }

}
