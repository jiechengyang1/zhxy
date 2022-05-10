package com.jcy.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jcy.zhxy.pojo.Admin;
import com.jcy.zhxy.pojo.LoginForm;

/**
* @author Administrator
* @description 针对表【tb_admin】的数据库操作Service
* @createDate 2022-05-06 10:18:56
*/
public interface AdminService extends IService<Admin> {


    Admin login(LoginForm loginForm);

    Admin getAdminById(Long userId);

    IPage<Admin> getAdminsByOpr(Page<Admin> pageParam, String adminName);
}
