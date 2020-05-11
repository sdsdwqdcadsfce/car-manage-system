package com.peait.carmanagesystem.controller;

import com.peait.carmanagesystem.entity.OrderManage;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.OrderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderManageController {
    @Autowired
    private OrderManageService orderManageService;
    /**
     * 订单信息新增
     * @param orderManage
     * @return
     */
    @CrossOrigin
    @PostMapping("/insert")
    public Result insert(@RequestBody OrderManage orderManage){
        return  orderManageService.insert(orderManage);
    }

    /**
     * 订单信息新增
     * @param orderManage
     * @return
     */
    @CrossOrigin
    @PostMapping("/webInsert")
    public Result webInsert(@RequestBody OrderManage orderManage){
        return  orderManageService.webInsert(orderManage);
    }


    /**
     * 订单信息修改
     * @param orderManage
     * @return
     */
    @CrossOrigin
    @PostMapping("/update")
    public Result updatg(@RequestBody OrderManage orderManage){
        return  orderManageService.update(orderManage);
    }

    /**
     * 订单信息查询
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping("/query")
    public Result query(@RequestParam(value = "id")String id){
        return  orderManageService.query(id);
    }
    /**
     * 订单信息删除
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping("/delete")
    public Result delete(@RequestParam(value = "id")String id){
        return  orderManageService.delete(id);
    }

    /**
     * 订单信息查询列表
     * @param ordercode
     * @param page
     * @param limit
     * @return
     */
    @CrossOrigin
    @GetMapping("/queryList")
    public TableResult queryList(@RequestParam(value = "ordercode",required = false) String ordercode,
                                 @RequestParam("page")int page,
                                 @RequestParam("limit")int limit
    ){
        return  orderManageService.queryList(ordercode,page,limit);
    }

    /**
     * 订单信息查询列表
     * @return
     */
    @CrossOrigin
    @GetMapping("/queryByUserName")
    public TableResult queryByUserName(@RequestParam(value = "userName") String userName
    ){
        return  orderManageService.queryByUserName(userName);
    }
}
