package com.peait.carmanagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.carmanagesystem.entity.AuthoritySet;
import com.peait.carmanagesystem.entity.WorkerInfo;
import com.peait.carmanagesystem.entity.vo.*;
import com.peait.carmanagesystem.exception.GlobalException;
import com.peait.carmanagesystem.mapper.AuthoritySetMapper;
import com.peait.carmanagesystem.mapper.UserInfoMapper;
import com.peait.carmanagesystem.mapper.WorkerInfoMapper;
import com.peait.carmanagesystem.result.CodeMsg;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.WorkerInfoService;
import com.peait.carmanagesystem.util.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 用户处理逻辑实现类
 */
@Service
public class WorkerInfoServiceImpl implements WorkerInfoService {
    @Resource
    private WorkerInfoMapper workerInfoMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private AuthoritySetMapper authoritySetMapper;

    /**
     * 获取员工列表
     * @param realName
     * @param page
     * @param limit
     * @return
     */
    @Override
    public TableResult getWorkList(String realName, int page, int limit)throws GlobalException {
        //使用pagehelper分页
        PageHelper.startPage(page, limit);
        List<WorkerInfo> result = workerInfoMapper.getWorkList(realName);
        PageInfo<WorkerInfo> userPageInfo = new PageInfo<>(result);
        return new TableResult(userPageInfo.getTotal(), userPageInfo.getList());
    }

    /**
     * 新增员工信息
     * @param registVO
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result insertWorker(RegistWorkVO registVO, HttpServletRequest request)  throws GlobalException{
        //通过id查询用户
        //加密算法
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(registVO.getPassword());
        //配置信息
        WorkerInfo workerInfo = new WorkerInfo();
        BeanUtils.copyProperties(registVO, workerInfo);
        workerInfo.setId(UUIDUtil.uuid());
        String workerCode = UUIDUtil.getSixNum();
        workerInfo.setWorkercode(workerCode);
        workerInfo.setPassword(encode);
        workerInfo.setInsertime(new Date());
        workerInfo.setUpdatetime(new Date());
        //新增员工信息
        workerInfoMapper.insertSelective(workerInfo);
        AuthoritySet authoritySet = new AuthoritySet();
        authoritySet.setId(UUIDUtil.uuid());
        authoritySet.setInsertime(new Date());
        //如果新增的员工不是admin 默认给0普通用户  后续再权限那边超级用户可以进行修改
        if(registVO.getWorkername().equals("admin")){
            authoritySet.setWorkerauthority("3");
        }else {
            authoritySet.setWorkerauthority("0");
        }
        authoritySet.setUpdatetime(new Date());
        authoritySet.setWorkercode(workerCode);
        authoritySet.setWorkername(registVO.getWorkername());
        //新增员工的权限 默认是 0普通  1 VIP  2 root超级用户
        authoritySetMapper.insertSelective(authoritySet);
        return Result.success("新增成功");
    }

    /**
     * 更新员工信息
     * @param workerInfoVO
     * @return
     */
    @Override
    public Result updateWorker(WorkerInfoVO workerInfoVO) throws GlobalException{
        //通过id查询用户
        WorkerInfo workerInfo = workerInfoMapper.selectByPrimaryKey(workerInfoVO.getId());
        if (workerInfo.getWorkername().equals("admin")) {
            throw new GlobalException(CodeMsg.ROOT_UPDATE_ERROR);
        }
        //判断用户名是否重复
        int count = userInfoMapper.IsExistValidataNotId("worker_info", "workerName", workerInfoVO.getWorkername(), workerInfoVO.getId());
        if (count > 0) {
            throw new GlobalException(CodeMsg.NAME_EXIXT_ERROR);
        }
        //判断手机号是否有重复
        int phoneCount = userInfoMapper.IsExistValidataNotId("worker_info", "workerPhone", workerInfoVO.getWorkerphone(), workerInfoVO.getId());
        if (phoneCount > 0) {
            throw new GlobalException(CodeMsg.MOBILE_EXIST);
        }

        //进行修改
        WorkerInfo worker= new WorkerInfo();
        BeanUtils.copyProperties(workerInfoVO, worker);
        worker.setUpdatetime(new Date());
        //密码加密
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String encode = bCrypt.encode(workerInfoVO.getPassword());
        worker.setPassword(encode);
        workerInfoMapper.updateByPrimaryKeySelective(worker);
        return Result.success("修改成功");


    }

    /**
     * 通过id查询员工信息
     * @param id
     * @return
     */
    @Override
    public Result selectWorkerById(String id) throws GlobalException{
        WorkerInfo workerInfo = workerInfoMapper.selectByPrimaryKey(id);
        return Result.success(workerInfo);
    }

    /**
     * 通过id删除员工
     * @param id
     * @param loginWorker
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteWorkerById(String id, String loginWorker) {
        AuthoritySet root  = authoritySetMapper.selectByWorkerCode(loginWorker);
        if(!root.getWorkername().equals("admin")){
            throw new GlobalException(CodeMsg.NOT_ROOT_ERROR);
        }
        //查询员工的信息
        //通过id查询用户
        WorkerInfo workerInfo = workerInfoMapper.selectByPrimaryKey(id);
        if (workerInfo.getWorkername().equals("admin")) {
            throw new GlobalException(CodeMsg.ROOT_ERROR);
        }
        //删除员工信息
        workerInfoMapper.deleteByPrimaryKey(id);
        //删除权限信息
        authoritySetMapper.deleteByWorkerCode(workerInfo.getWorkercode());
        return Result.success("删除成功");
    }

    /**
     * 登陆操作
     *
     * @param loginVO
     * @return
     */
    @Override
    public Result login(LoginVO loginVO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String userName = loginVO.getUserName();
        String passWord = loginVO.getPassWord();
        WorkerInfo workerInfo = workerInfoMapper.selectUserByWorkerName(userName);
        boolean matches = bCryptPasswordEncoder.matches(passWord, workerInfo.getPassword());
        if (!matches) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
//        String token = UUIDUtil.uuid();
        String token = workerInfo.getWorkercode();
////        addCookie(response, token, user);
        //返回登陆成功
        return Result.success(token);
    }

}
