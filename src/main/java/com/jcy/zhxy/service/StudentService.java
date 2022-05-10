package com.jcy.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jcy.zhxy.pojo.LoginForm;
import com.jcy.zhxy.pojo.Student;

/**
* @author Administrator
* @description 针对表【tb_student】的数据库操作Service
* @createDate 2022-05-06 10:20:03
*/
public interface StudentService extends IService<Student> {

    Student login(LoginForm loginForm);

    Student getStudentById(Long userId);

    IPage<Student> getStudentByOpr(Page<Student> pageParam, Student student);
}
