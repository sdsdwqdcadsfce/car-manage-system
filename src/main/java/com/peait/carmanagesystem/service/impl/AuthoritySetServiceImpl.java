package com.peait.carmanagesystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.carmanagesystem.entity.AuthoritySet;
import com.peait.carmanagesystem.exception.GlobalException;
import com.peait.carmanagesystem.mapper.AuthoritySetMapper;
import com.peait.carmanagesystem.result.CodeMsg;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.AuthoritySetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/***
 * 权限配置的实现类
 */
@Service
public class AuthoritySetServiceImpl implements AuthoritySetService {
    @Resource
    private AuthoritySetMapper authoritySetMapper;
    /**
     * 通过员工的code 查询权限
     * @param workerCode
     * @return
     */
    @Override
    public Result queryByWorkerCode(String workerCode) throws GlobalException {
        AuthoritySet  authoritySet  = authoritySetMapper.selectByWorkerCode(workerCode);
        return Result.success(authoritySet);
    }

    /**
     * 修改用户的权限
     * @param workerCode
     * @param authority
     * @return
     */
    @Override
    public Result updateWorkerAuthority(String loginCode,String workerCode, String authority) throws GlobalException{
        AuthoritySet  root  = authoritySetMapper.selectByWorkerCode(loginCode);
        if(!root.getWorkername().equals("admin")){
            throw new GlobalException(CodeMsg.NOT_ROOT_ERROR);
        }
        AuthoritySet  authoritySet  = authoritySetMapper.selectByWorkerCode(workerCode);
        authoritySet.setWorkerauthority(authority);
        authoritySetMapper.updateByPrimaryKeySelective(authoritySet);
        return Result.success("操作成功");
    }

    /**
     * 查询用户列表
     * @param workerName
     * @param page
     * @param limit
     * @return
     */
    @Override
    public TableResult queryList(String workerName, Integer page, Integer limit) throws GlobalException{
        PageHelper.startPage(page,limit);
        List<AuthoritySet> result  =authoritySetMapper.selectByWorkerName(workerName);
        PageInfo<AuthoritySet> authoritySetPageInfo = new PageInfo<>(result);
        return new TableResult(authoritySetPageInfo.getTotal(),authoritySetPageInfo.getList());
    }
}
