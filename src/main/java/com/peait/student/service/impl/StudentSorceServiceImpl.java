package com.peait.student.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.student.entity.Student;
import com.peait.student.entity.StudentSorce;
import com.peait.student.mapper.StudentSorceMapper;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;
import com.peait.student.service.StudentSorceServiice;
import com.peait.student.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentSorceServiceImpl implements StudentSorceServiice {
    @Resource
    private StudentSorceMapper studentSorceMapper;
    @Override
    public TableResult getList(String subjectName, int page, int limit) {
        //使用pagehelper分页
        PageHelper.startPage(page, limit);
        List<StudentSorce> result = studentSorceMapper.getList(subjectName);
        PageInfo<StudentSorce> userPageInfo = new PageInfo<>(result);
        return new TableResult(userPageInfo.getTotal(), userPageInfo.getList());
    }

    @Override
    public Result insert(StudentSorce studentSorce) {
        studentSorce.setId(UUIDUtil.uuid());
        studentSorceMapper.insertSelective(studentSorce);
        return Result.success("新增成功");
    }

    @Override
    public Result update(StudentSorce studentSorce) {
        studentSorceMapper.updateByPrimaryKeySelective(studentSorce);
        return Result.success("修改成功");
    }

    @Override
    public Result deleteById(String id) {
        studentSorceMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功");
    }

    @Override
    public Result selectById(String id) {
        StudentSorce studentSorce = studentSorceMapper.selectByPrimaryKey(id);
        return Result.success(studentSorce);
    }
}
