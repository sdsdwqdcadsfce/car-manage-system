package com.peait.carmanagesystem.controller;

import com.peait.carmanagesystem.entity.vo.RegistProjectBeautyManageVO;
import com.peait.carmanagesystem.result.Result;
import com.peait.carmanagesystem.result.TableResult;
import com.peait.carmanagesystem.service.ProjectManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 美容项目管理
 */
@RestController
@RequestMapping("/project")
public class ProjectManageController {
    @Autowired
    private ProjectManageService projectManageService;

    /**
     * 美容项目新增
     */
    @CrossOrigin
    @PostMapping("/insert")
    public Result insert(@RequestBody @Valid RegistProjectBeautyManageVO registProjectBeautyManageVO , BindingResult result){
        if(result.hasErrors()){
            return Result.error(result);
        }
       return projectManageService.insert(registProjectBeautyManageVO);
    }
    /**
     * 查询美容项目
     */
    @CrossOrigin
    @GetMapping("/query")
    public Result query(@RequestParam("id")String id){
        return projectManageService.query(id);
    }

    /**
     * 删除美容项目
     */
    @CrossOrigin
    @GetMapping("/delete")
    public Result delete(@RequestParam("id")String id){
        return projectManageService.delete(id);
    }

    /**
     * 查询列表
     */
    @CrossOrigin
    @GetMapping("/queryList")
    public TableResult queryList(@RequestParam(value = "projectname",required = false)String projectname,@RequestParam("page")int page,@RequestParam("limit")int limit){
        return projectManageService.queryList(projectname,page,limit);
    }
    /**
     * 查询列表
     */
    @CrossOrigin
    @GetMapping("/queryName")
    public Result queryName(){
        return projectManageService.queryName();
    }
    /**
     * 修改美容项目
     */
    @CrossOrigin
    @PostMapping("/update")
    public Result update(@RequestBody @Valid RegistProjectBeautyManageVO registProjectBeautyManageVO , BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(result);
        }
        return projectManageService.update(registProjectBeautyManageVO);
    }


}
