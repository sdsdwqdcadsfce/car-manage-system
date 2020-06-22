package com.peait.student.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.student.entity.Books;
import com.peait.student.entity.Testinfo;
import com.peait.student.mapper.TestinfoMapper;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;
import com.peait.student.service.TestinfoService;
import com.peait.student.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestinfoServiceImpl implements TestinfoService {

    @Resource
    private TestinfoMapper testinfoMapper;
    @Override
    public Result selectById(String id) {
        Testinfo books = testinfoMapper.selectByPrimaryKey(id);
        return Result.success(books);
    }

    @Override
    public Result deleteById(String id) {
        int i = testinfoMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功");
    }

    @Override
    public Result update(Testinfo testinfo) {
        testinfoMapper.updateByPrimaryKeySelective(testinfo);
        return Result.success("修改成功");
    }

    @Override
    public Result insert(Testinfo testinfo) {
        testinfo.setId(UUIDUtil.uuid());
        testinfoMapper.insertSelective(testinfo);
        return Result.success("新增成功");
    }

    @Override
    public TableResult getList(String subjectName, int page, int limit) {
        //使用pagehelper分页
        PageHelper.startPage(page, limit);
        List<Testinfo> result = testinfoMapper.getList(subjectName);
        PageInfo<Testinfo> userPageInfo = new PageInfo<>(result);
        return new TableResult(userPageInfo.getTotal(), userPageInfo.getList());
    }
}
