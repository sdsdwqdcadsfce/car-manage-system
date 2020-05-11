package com.peait.carmanagesystem.controller;

import com.peait.carmanagesystem.entity.CarInfo;
import com.peait.carmanagesystem.entity.vo.RegistCarInfoVO;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 车辆信息的管理
 */
@RestController
@RequestMapping("/car")
public class CarInfoController {
    /**
     * 新增车辆信息
     */
    @Autowired
    private CarInfoService carInfoService;

    @PostMapping("/insert")
    @CrossOrigin
    public Result insert(@RequestBody @Valid RegistCarInfoVO registCarInfoVO, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(result);
        }
        return carInfoService.insert(registCarInfoVO);
    }

    /**
     * 修改车辆信息
     */
    @CrossOrigin
    @PostMapping("/update")
    public Result update(@RequestBody RegistCarInfoVO registCarInfoVO) {
        return carInfoService.update(registCarInfoVO);
    }

    /**
     * 删除车辆信息
     */
    @CrossOrigin
    @GetMapping("/delete")
    public Result delete(@RequestParam("id") String id) {
        return carInfoService.delete(id);
    }

    /**
     * 通过id查询车辆
     */
    @CrossOrigin
    @GetMapping("/queryById")
    public Result queryById(@RequestParam("id") String id) {
        return carInfoService.queryById(id);
    }

    /**
     * 查询车辆信息列表
     */
    @CrossOrigin
    @GetMapping("/queryList")
    public TableResult delete(@RequestParam(value = "carUserName", required = false) String carUserName,
                              @RequestParam("page") int page,
                              @RequestParam("limit") int limit) {
        return carInfoService.queryList(carUserName,page,limit);
    }

}
