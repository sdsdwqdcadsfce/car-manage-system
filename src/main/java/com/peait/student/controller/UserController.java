package com.peait.student.controller;
import com.peait.student.entity.vo.LoginVO;
import com.peait.student.entity.vo.RegistUserVO;
import com.peait.student.entity.vo.UserInfoVO;
import com.peait.student.result.Result;
import com.peait.student.result.TableResult;
import com.peait.student.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 用户信息操作接口
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    //获取学生列表
    //解决跨域问题
    @CrossOrigin
    @GetMapping("/getList")
    public TableResult getUserList(@RequestParam(value = "realName",required = false)String realName,
                                   @RequestParam(value = "page")int page,
                                   @RequestParam(value = "limit")int limit
    ){
        return userInfoService.getUserList(realName,page,limit);
    }

    //新增用户
    @CrossOrigin
    @PostMapping("/insert")
    public Result registUser(@RequestBody @Valid RegistUserVO registVO , BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            return Result.error(result);
        }
        return userInfoService.insertUser(registVO,request);
    }


    //修改用户
    @CrossOrigin
    @PostMapping("/update")
    public Result updateUser(@RequestBody @Valid UserInfoVO updateVO , BindingResult result,HttpServletRequest request){
        if(result.hasErrors()){
            return Result.error(result);
        }
        return userInfoService.updateUser(updateVO,request);
    }


    //通过id查询用户
    @CrossOrigin
    @GetMapping("/queryUserById")
    public Result registUser(@RequestParam(value = "id")String id){
        return userInfoService.selectUserById(id);
    }

    //登陆
    //解决跨域问题
    @CrossOrigin
    @PostMapping(value = "/login")
    public Result login(@RequestBody @Valid LoginVO loginVO, BindingResult result){
        if(result.hasErrors()){
            return Result.error(result);
        }
        String userName = loginVO.getUserName();
        String passWord = loginVO.getPassWord();
        return userInfoService.loginUser(userName,passWord);
    }


    //通过查询用户的code name
    @CrossOrigin
    @GetMapping("/queryUser")
    public Result registUser(){
        return userInfoService.queryUser();
    }


    //删除用户
    @CrossOrigin
    @GetMapping("/deleteUserById")
    public Result deleteUserById(@RequestParam(value = "id")String id,HttpServletRequest request){
        return userInfoService.deleteUserById(id,request);
    }




}
