package com.jcy.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcy.zhxy.mapper.TeacherMapper;
import com.jcy.zhxy.pojo.LoginForm;
import com.jcy.zhxy.pojo.Teacher;
import com.jcy.zhxy.service.TeacherService;
import com.jcy.zhxy.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Administrator
* @description 针对表【tb_teacher】的数据库操作Service实现
* @createDate 2022-05-06 10:20:06
*/
@Service("teacherServiceImpl")
@Transactional
public class  TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

    @Override
    public Teacher login(LoginForm loginForm) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("name",loginForm.getUsername()).eq("password", MD5.encrypt(loginForm.getPassword()));
        Teacher teacher = baseMapper.selectOne(queryWrapper);
        return teacher;
    }

    @Override
    public Teacher getTeachertById(Long userId) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        return baseMapper.selectOne(queryWrapper);
    }
}




