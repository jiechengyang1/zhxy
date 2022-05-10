package com.jcy.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcy.zhxy.pojo.Grade;
import com.jcy.zhxy.service.GradeService;
import com.jcy.zhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "年级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {
    @Autowired
    GradeService gradeService;
    @ApiOperation("根据年纪名称查询成绩")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGradees(@ApiParam("分页页码数") @PathVariable("pageNo") Integer pageNo,
                             @ApiParam("分页大小") @PathVariable("pageSize") Integer pageSize,
                             @ApiParam("查询条件") String gradeName){
        Page<Grade> page =new Page<>(pageNo,pageSize);

        IPage<Grade> pageRs= gradeService.getGradeByOpr(page,gradeName);
        return Result.ok(pageRs);
    }
    @ApiOperation("新增vs修改grade")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveorupdate(
            @ApiParam("Json的grade对象") @RequestBody Grade grade){
        gradeService.saveOrUpdate(grade);

        return Result.ok();
    }
    @ApiOperation("删除Grade信息")
    @DeleteMapping("/deleteGrade")
    public Result deletegrade(@ApiParam("需要删除的的成绩id--Json列表") @RequestBody List<Integer> list){
        gradeService.removeByIds(list);
        return Result.ok();
    }
    @ApiOperation("获取所有年纪")
    @GetMapping("/getGrades")
    public Result getgrades(){
        List<Grade> grades=gradeService.getGrades();
        return Result.ok(grades);
    }

}
