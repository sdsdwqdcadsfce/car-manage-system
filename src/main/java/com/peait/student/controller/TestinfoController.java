package com.peait.student.controller;

import com.peait.student.entity.Books;
import com.peait.student.entity.Testinfo;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;
import com.peait.student.service.BooksService;
import com.peait.student.service.TestinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestinfoController {

    @Autowired
    private TestinfoService testinfoService;

    //获取学生列表
    //解决跨域问题
    @CrossOrigin
    @GetMapping("/getList")
    public TableResult getUserList(@RequestParam(value = "subjectName",required = false)String subjectName,
                                   @RequestParam(value = "page")int page,
                                   @RequestParam(value = "limit")int limit
    ){
        return testinfoService.getList(subjectName,page,limit);
    }

    //解决跨域问题
    //新增学生信息
    @CrossOrigin
    @PostMapping("/insert")
    public Result registUser(@RequestBody Testinfo testinfo ){
        return testinfoService.insert(testinfo);
    }


    //修改学生信息
    @CrossOrigin
    @PostMapping("/update")
    public Result updateUser(@RequestBody Testinfo testinfo){
        return testinfoService.update(testinfo);
    }

    //删除学生信息
    @CrossOrigin
    @GetMapping("/delete")
    public Result deleteUserById(@RequestParam(value = "id")String id){
        return testinfoService.deleteById(id);
    }


    //通过id查询学生信息
    @CrossOrigin
    @GetMapping("/queryyId")
    public Result registUser(@RequestParam(value = "id")String id){
        return testinfoService.selectById(id);
    }
}
