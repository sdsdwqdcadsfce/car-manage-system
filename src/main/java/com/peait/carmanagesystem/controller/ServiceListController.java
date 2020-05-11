package com.peait.carmanagesystem.controller;

import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.service.ServiceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务列表的接口
 */
@RestController
public class ServiceListController {
    @Autowired
    private ServiceListService serviceListService;
    @GetMapping("/query")
    @CrossOrigin
    public Result query(){
        return serviceListService.query();
    }
}
