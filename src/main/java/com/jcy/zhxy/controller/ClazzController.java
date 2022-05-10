package com.jcy.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcy.zhxy.pojo.Clazz;
import com.jcy.zhxy.service.ClazzService;
import com.jcy.zhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "班级管理器")
@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {

    @Autowired
    ClazzService clazzService;
    @ApiOperation("分页条件查询")
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getclazzByopr(
            @ApiParam("分页查询的页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("分页查询页大小")@PathVariable("pageSize") Integer pageSize,
            @ApiParam("分页查询的查询条件") Clazz clazz
    ){
        Page<Clazz> page =new Page<>(pageNo,pageSize);

        IPage<Clazz> iPage=clazzService.getClazzsByOpr(page,clazz);

        return Result.ok(iPage);

    }
    @ApiOperation("增加或者修改班级信息")
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(
            @ApiParam("JSON格式的班级信息")@RequestBody Clazz clazz
    ){
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }
    @ApiOperation("删除单个或者多个班级")
@DeleteMapping("/deleteClazz")
    public Result deleteClazz(
            @ApiParam("删除班级数组") @RequestBody List<Integer> ids){
        clazzService.removeByIds(ids);
    return Result.ok();
}


    @GetMapping("/getClazzs")
    @ApiOperation("查询所有班级信息")
    public Result getclazz(){
        List<Clazz> clazzes=clazzService.getClazzs();
        return Result.ok(clazzes);
    }



}
