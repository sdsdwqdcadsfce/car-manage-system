package com.peait.carmanagesystem.controller;

import com.peait.carmanagesystem.entity.StockManange;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.StockManangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 库存管理
 */
@RestController
@RequestMapping("/stock")
public class StockManangeController {
    @Autowired
    private StockManangeService stockManangeService;

    /**
     * 库存新增
     */
    @CrossOrigin
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody @Valid StockManange stockManange, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(result);
        }
        return stockManangeService.insert(stockManange);
    }

    /**
     * 库存更新
     */
    @CrossOrigin
    @PostMapping(value = "/update")
    public Result update(@RequestBody StockManange stockManange) {
        return stockManangeService.update(stockManange);
    }

    /**
     * 库存查询
     */
    @CrossOrigin
    @GetMapping(value = "/query")
    public Result query(@RequestParam("id") String id) {
        return stockManangeService.query(id);
    }

    /**
     * 库存查询列表
     */
    @CrossOrigin
    @GetMapping(value = "/queryList")
    public TableResult query(@RequestParam(value = "goodsName", required = false) String goodsName,
                             @RequestParam("page") int page,
                             @RequestParam("limit") int limit) {
        return stockManangeService.queryList(goodsName,page,limit);
    }

    /**
     * 库存删除
     */
    @CrossOrigin
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam("id") String id) {
        return stockManangeService.delete(id);
    }
}
