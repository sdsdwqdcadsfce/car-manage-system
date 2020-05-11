package com.peait.carmanagesystem.controller;

import com.peait.carmanagesystem.entity.vo.CarBeautyManageVO;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.CarBeautyManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carManage")
public class CarBeautyManageController {
    @Autowired
    private CarBeautyManageService carBeautyManageService;

    @CrossOrigin
    @PostMapping("/insert")
    public Result insert(@RequestBody CarBeautyManageVO carBeautyManageVO) {
       return  carBeautyManageService.insert(carBeautyManageVO);
    }

    @CrossOrigin
    @PostMapping("/update")
    public Result update(@RequestBody CarBeautyManageVO carBeautyManageVO) {
        return  carBeautyManageService.update(carBeautyManageVO);
    }

    @CrossOrigin
    @GetMapping("/delete")
    public Result delete(@RequestParam("id")String id ){
        return  carBeautyManageService.delete(id);
    }


    @CrossOrigin
    @GetMapping("/query")
    public Result query(@RequestParam("id")String id ){
        return  carBeautyManageService.query(id);
    }

    @CrossOrigin
    @GetMapping("/queryList")
    public TableResult queryList(@RequestParam("carCode")String carCode ,
                                 @RequestParam("page")int page ,
                                 @RequestParam("limit")int limit
                                 ){
        return  carBeautyManageService.queryList(carCode,page,limit);
    }


}
