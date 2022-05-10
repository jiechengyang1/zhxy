package com.jcy.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcy.zhxy.pojo.Student;
import com.jcy.zhxy.service.StudentService;
import com.jcy.zhxy.utils.MD5;
import com.jcy.zhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "学生控制器")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {

    @Autowired
    StudentService studentService;

    @ApiOperation("分页带条件查询学生信息")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentBuOpr(
            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("查询的条件") Student student
    ){
        //分页信息封装Page对象
        Page<Student> pageParam =new Page(pageNo,pageSize);
        // 进行查询
        IPage<Student> studentPage =studentService.getStudentByOpr(pageParam,student);
        // 封装Result返回
        return Result.ok(studentPage);
    }

    @ApiOperation("保存修改学生信息")
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(
            @ApiParam("修改学生") @RequestBody Student student
    ){
        Integer id = student.getId();
        if (id == null||id==0){
            student.setPassword(MD5.encrypt(student.getPassword()));
        }
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    @ApiOperation("删除学生")
    @DeleteMapping("/delStudentById")
    public Result deletestudent(
            @ApiParam("删除的学生集合") @RequestBody List<Integer> ids
    ){
        studentService.removeByIds(ids);
        return Result.ok();
    }
    @ApiOperation("删除学生")
    @DeleteMapping("/delStudentById1")
//    master  @DeleteMapping("/delStudentById1")
//    @DeleteMapping("/delStudentById1")
    public Result deletestudent1(
            @ApiParam("删除的学生集合") @RequestBody List<Integer> ids
    ){
        studentService.removeByIds(ids);
        System.out.println("fuck");
        return Result.ok();
    }
}
