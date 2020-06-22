package com.peait.student.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.student.entity.Student;
import com.peait.student.entity.Subject;
import com.peait.student.mapper.SubjectMapper;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;
import com.peait.student.service.SubjectService;
import com.peait.student.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Resource
    private SubjectMapper subjectMapper;
    @Override
    public TableResult getStudentList(String studentName, int page, int limit) {
        //使用pagehelper分页
        PageHelper.startPage(page, limit);
        List<Subject> result = subjectMapper.getList(studentName);
        PageInfo<Subject> userPageInfo = new PageInfo<>(result);
        return new TableResult(userPageInfo.getTotal(), userPageInfo.getList());
    }

    @Override
    public Result insertUser(Subject student) {
        student.setId(UUIDUtil.uuid());
        subjectMapper.insertSelective(student);
        return Result.success("新增成功");
    }

    @Override
    public Result updateStudent(Subject student) {
        subjectMapper.updateByPrimaryKeySelective(student);
        return Result.success("修改成功");
    }

    @Override
    public Result deleteUserById(String id) {
        subjectMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功");
    }

    @Override
    public Result selectUserById(String id) {
        Subject subject = subjectMapper.selectByPrimaryKey(id);
        return Result.success(subject);
    }
}
