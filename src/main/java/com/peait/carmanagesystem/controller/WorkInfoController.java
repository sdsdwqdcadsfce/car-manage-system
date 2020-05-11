package com.peait.carmanagesystem.controller;


import com.peait.carmanagesystem.entity.AuthoritySet;
import com.peait.carmanagesystem.entity.vo.LoginVO;
import com.peait.carmanagesystem.entity.vo.RegistWorkVO;
import com.peait.carmanagesystem.entity.vo.WorkerInfoVO;
import com.peait.carmanagesystem.exception.GlobalException;
import com.peait.carmanagesystem.result.CodeMsg;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.WorkerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 员工信息操作接口
 */
@RestController
@RequestMapping("/worker")
public class WorkInfoController {
    @Autowired
    private WorkerInfoService workerInfoService;
    //登陆
    //解决跨域问题
    @CrossOrigin
    @PostMapping(value = "/login")
    public Result login(@RequestBody @Valid LoginVO loginVO, BindingResult result){
        if(result.hasErrors()){
            return Result.error(result);
        }
        return  workerInfoService.login(loginVO);
    }

    //获取用户列表
    //解决跨域问题
    @CrossOrigin
    @GetMapping("/getList")
    public TableResult getUserList(@RequestParam(value = "realName",required = false)String realName,
                                   @RequestParam(value = "page")int page,
                                   @RequestParam(value = "limit")int limit
    ){
        return workerInfoService.getWorkList(realName,page,limit);
    }

    //新增用户
    @CrossOrigin
    @PostMapping("/insert")
    public Result registWorker(@RequestBody @Valid RegistWorkVO registVO , BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            return Result.error(result);
        }
        return workerInfoService.insertWorker(registVO,request);
    }


    //修改员工
    @CrossOrigin
    @PostMapping("/update")
    public Result updateWorker(@RequestBody @Valid WorkerInfoVO workerInfoVO , BindingResult result){
        if(result.hasErrors()){
            return Result.error(result);
        }
        return workerInfoService.updateWorker(workerInfoVO);
    }


    //通过id查询员工
    @CrossOrigin
    @GetMapping("/queryWorkerById")
    public Result selectWorkerById(@RequestParam(value = "id")String id){
        return workerInfoService.selectWorkerById(id);
    }

    //删除员工
    @CrossOrigin
    @GetMapping("/deleteWorkerById")
    public Result deleteWorkerById(@RequestParam(value = "id")String id,HttpServletRequest request){
        String  loginWorker = request.getHeader("token");
        return workerInfoService.deleteWorkerById(id,loginWorker);
    }

}
