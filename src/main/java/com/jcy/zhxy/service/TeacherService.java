package com.jcy.zhxy.service;

import com.jcy.zhxy.pojo.LoginForm;
import com.jcy.zhxy.pojo.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【tb_teacher】的数据库操作Service
* @createDate 2022-05-06 10:20:06
*/
public interface TeacherService extends IService<Teacher> {

    Teacher login(LoginForm loginForm);

    Teacher getTeachertById(Long userId);
}
