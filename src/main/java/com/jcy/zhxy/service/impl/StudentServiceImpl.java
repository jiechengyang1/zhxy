package com.jcy.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcy.zhxy.mapper.StudentMapper;
import com.jcy.zhxy.pojo.LoginForm;
import com.jcy.zhxy.pojo.Student;
import com.jcy.zhxy.service.StudentService;
import com.jcy.zhxy.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Administrator
* @description 针对表【tb_student】的数据库操作Service实现
* @createDate 2022-05-06 10:20:03
*/
@Service("studentServiceImpl")
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

    @Override
    public Student login(LoginForm loginForm) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

       queryWrapper.eq("name",loginForm.getUsername()).eq("password", MD5.encrypt(loginForm.getPassword()));
        Student student = baseMapper.selectOne(queryWrapper);
        return student;

    }

    @Override
    public Student getStudentById(Long userId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
       Student student = baseMapper.selectOne(queryWrapper);
        return student;
    }

    @Override
    public IPage<Student> getStudentByOpr(Page<Student> pageParam, Student student) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(student.getName())){
            queryWrapper.like("name",student.getName());
        }
        if(!StringUtils.isEmpty(student.getClazzName())){
            queryWrapper.like("clazz_name",student.getClazzName());
        }
        queryWrapper.orderByDesc("id");
        Page<Student> studentPage = baseMapper.selectPage(pageParam, queryWrapper);
        return studentPage;
    }

}




