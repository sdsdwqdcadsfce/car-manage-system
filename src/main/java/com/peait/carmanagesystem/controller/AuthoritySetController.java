package com.peait.carmanagesystem.controller;

import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.AuthoritySetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限的接口
 */
@RestController
@RequestMapping("/authority")
public class AuthoritySetController {
    @Autowired
    private AuthoritySetService authoritySetService;

    /**
     * 查询用户的权限
     */
    @GetMapping("/queryWorkerAuthority")
    @CrossOrigin
    public Result queryWorkerAuthority(@RequestParam("workerCode")String workerCode){
        return  authoritySetService.queryByWorkerCode(workerCode);
    }
    /**
     * 修改用户的权限
     */
    @GetMapping("/updateWorkerAuthority")
    @CrossOrigin
    public Result updateWorkerAuthority(HttpServletRequest request, @RequestParam("workerCode") String workerCode, @RequestParam("authority") String authority){
        String  loginWorker = request.getHeader("token");
        return  authoritySetService.updateWorkerAuthority(loginWorker,workerCode,authority);
    }

    /**
     * 查询用户的列表
     */
    @GetMapping("/queryList")
    @CrossOrigin
    public TableResult queryList(@RequestParam(value = "workerName",required = false)String workerName,
                                 @RequestParam("page")Integer page,
                                 @RequestParam("limit")Integer limit){
        return  authoritySetService.queryList(workerName,page,limit);
    }


}
