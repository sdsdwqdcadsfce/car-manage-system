package com.peait.student.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peait.student.entity.Student;
import com.peait.student.exception.GlobalException;
import com.peait.student.mapper.StudentMapper;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;
import com.peait.student.service.StudentService;
import com.peait.student.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    /**
     * 获取学生列表
     * @param page
     * @param limit
     * @return
     */
    @Override
    public TableResult getStudentList(String studentName, int page, int limit) throws GlobalException {
        //使用pagehelper分页
        PageHelper.startPage(page, limit);
        List<Student> result = studentMapper.getStudentList(studentName);
        PageInfo<Student> userPageInfo = new PageInfo<>(result);
        return new TableResult(userPageInfo.getTotal(), userPageInfo.getList());
    }

    /**
     * 新增学生信息
     * @param student
     * @return
     */
    @Override
    public Result insertUser(Student student) throws GlobalException{
        student.setId(UUIDUtil.uuid());
        studentMapper.insertSelective(student);
        return Result.success("新增成功");
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    @Override
    public Result updateStudent(Student student) throws GlobalException{
        studentMapper.updateByPrimaryKeySelective(student);
        return Result.success("修改成功");
    }

    /**
     * 删除学生信息
     * @param id
     * @return
     */
    @Override
    public Result deleteUserById(String id)throws GlobalException {
        studentMapper.deleteByPrimaryKey(id);
        return Result.success("修改成功");
    }

    /**
     *  查询学生信息
     * @param id
     * @return
     */
    @Override
    public Result selectUserById(String id)throws GlobalException {
        Student student = studentMapper.selectByPrimaryKey(id);
        return Result.success(student);
    }


}
