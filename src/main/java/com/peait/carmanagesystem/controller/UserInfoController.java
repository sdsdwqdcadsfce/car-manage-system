package com.peait.carmanagesystem.controller;
import com.peait.carmanagesystem.entity.UserInfo;
import com.peait.carmanagesystem.entity.vo.RegistUserVO;
import com.peait.carmanagesystem.entity.vo.UserInfoVO;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.UserInfoService;
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
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    //获取用户列表
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

    //用户登陆
    @CrossOrigin
    @GetMapping("/login")
    public Result registUser(@RequestParam(value = "userName")String userName,@RequestParam("passWord")String password){
        return userInfoService.loginUser(userName,password);
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
