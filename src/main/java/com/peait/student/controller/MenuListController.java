package com.peait.student.controller;


import com.peait.student.result.Result;
import com.peait.student.service.MenuListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取菜单
 */
@RestController
public class MenuListController {
    @Autowired
    private MenuListService menuListService;

    @GetMapping("/getMenu")
    @CrossOrigin
    public Result getEmnuList(HttpServletRequest request){
        return menuListService.queryEmnuList(request.getHeader("token"));
    }
}
