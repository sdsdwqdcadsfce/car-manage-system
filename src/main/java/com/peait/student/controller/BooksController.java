package com.peait.student.controller;

import com.peait.student.entity.Books;
import com.peait.student.entity.Student;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;
import com.peait.student.service.BooksService;
import com.peait.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BooksService booksService;

    //获取学生列表
    //解决跨域问题
    @CrossOrigin
    @GetMapping("/getList")
    public TableResult getUserList(@RequestParam(value = "bookName",required = false)String bookName,
                                   @RequestParam(value = "page")int page,
                                   @RequestParam(value = "limit")int limit
    ){
        return booksService.getList(bookName,page,limit);
    }

    //解决跨域问题
    //新增学生信息
    @CrossOrigin
    @PostMapping("/insert")
    public Result registUser(@RequestBody Books books ){
        return booksService.insert(books);
    }


    //修改学生信息
    @CrossOrigin
    @PostMapping("/update")
    public Result updateUser(@RequestBody Books books){
        return booksService.update(books);
    }

    //删除学生信息
    @CrossOrigin
    @GetMapping("/delete")
    public Result deleteUserById(@RequestParam(value = "id")String id){
        return booksService.deleteById(id);
    }


    //通过id查询学生信息
    @CrossOrigin
    @GetMapping("/queryyId")
    public Result registUser(@RequestParam(value = "id")String id){
        return booksService.selectById(id);
    }
}
