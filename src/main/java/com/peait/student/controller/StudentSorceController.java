package com.peait.student.controller;

import com.peait.student.entity.Books;
import com.peait.student.entity.StudentSorce;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;
import com.peait.student.service.StudentSorceServiice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentSorce")
public class StudentSorceController {
    @Autowired
    private StudentSorceServiice studentSorceServiice;
    //获取学生列表
    //解决跨域问题
    @CrossOrigin
    @GetMapping("/getList")
    public TableResult getUserList(@RequestParam(value = "subjectName",required = false)String subjectName,
                                   @RequestParam(value = "page")int page,
                                   @RequestParam(value = "limit")int limit
    ){
        return studentSorceServiice.getList(subjectName,page,limit);
    }

    //解决跨域问题
    //新增学生信息
    @CrossOrigin
    @PostMapping("/insert")
    public Result registUser(@RequestBody StudentSorce studentSorce ){
        return studentSorceServiice.insert(studentSorce);
    }


    //修改学生信息
    @CrossOrigin
    @PostMapping("/update")
    public Result updateUser(@RequestBody StudentSorce studentSorce){
        return studentSorceServiice.update(studentSorce);
    }

    //删除学生信息
    @CrossOrigin
    @GetMapping("/delete")
    public Result deleteById(@RequestParam(value = "id")String id){
        return studentSorceServiice.deleteById(id);
    }


    //通过id查询学生信息
    @CrossOrigin
    @GetMapping("/queryyId")
    public Result regist(@RequestParam(value = "id")String id){
        return studentSorceServiice.selectById(id);
    }
}
