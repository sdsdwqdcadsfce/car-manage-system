package com.peait.student.controller;

import com.peait.student.entity.Student;
import com.peait.student.entity.Subject;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;
import com.peait.student.service.StudentService;
import com.peait.student.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //获取学生列表
    //解决跨域问题
    @CrossOrigin
    @GetMapping("/getList")
    public TableResult getUserList(@RequestParam(value = "subjectName",required = false)String subjectName,
                                   @RequestParam(value = "page")int page,
                                   @RequestParam(value = "limit")int limit
    ){
        return subjectService.getStudentList(subjectName,page,limit);
    }

    //解决跨域问题
    //新增学生信息
    @CrossOrigin
    @PostMapping("/insert")
    public Result registUser(@RequestBody Subject student ){
        return subjectService.insertUser(student);
    }


    //修改学生信息
    @CrossOrigin
    @PostMapping("/update")
    public Result updateUser(@RequestBody Subject student){
        return subjectService.updateStudent(student);
    }

    //删除学生信息
    @CrossOrigin
    @GetMapping("/delete")
    public Result deleteUserById(@RequestParam(value = "id")String id){
        return subjectService.deleteUserById(id);
    }


    //通过id查询学生信息
    @CrossOrigin
    @GetMapping("/queryStudentById")
    public Result registUser(@RequestParam(value = "id")String id){
        return subjectService.selectUserById(id);
    }
}
