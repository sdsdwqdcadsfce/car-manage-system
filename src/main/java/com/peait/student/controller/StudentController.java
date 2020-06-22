package com.peait.student.controller;

import com.peait.student.entity.Student;
import com.peait.student.entity.vo.RegistUserVO;
import com.peait.student.entity.vo.UserInfoVO;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;
import com.peait.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //获取学生列表
    //解决跨域问题
    @CrossOrigin
    @GetMapping("/getList")
    public TableResult getUserList(@RequestParam(value = "studentName",required = false)String studentName,
                                   @RequestParam(value = "page")int page,
                                   @RequestParam(value = "limit")int limit
    ){
        return studentService.getStudentList(studentName,page,limit);
    }

    //解决跨域问题
    //新增学生信息
    @CrossOrigin
    @PostMapping("/insert")
    public Result registUser(@RequestBody Student student ){
        return studentService.insertUser(student);
    }


    //修改学生信息
    @CrossOrigin
    @PostMapping("/update")
    public Result updateUser(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    //删除学生信息
    @CrossOrigin
    @GetMapping("/delete")
    public Result deleteUserById(@RequestParam(value = "id")String id){
        return studentService.deleteUserById(id);
    }


    //通过id查询学生信息
    @CrossOrigin
    @GetMapping("/queryStudentById")
    public Result registUser(@RequestParam(value = "id")String id){
        return studentService.selectUserById(id);
    }
}
