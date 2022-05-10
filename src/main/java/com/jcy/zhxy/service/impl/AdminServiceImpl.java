package com.jcy.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcy.zhxy.mapper.AdminMapper;
import com.jcy.zhxy.pojo.Admin;
import com.jcy.zhxy.pojo.LoginForm;
import com.jcy.zhxy.service.AdminService;
import com.jcy.zhxy.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Administrator
* @description 针对表【tb_admin】的数据库操作Service实现
* @createDate 2022-05-06 10:18:56
*/
@Service("adminServiceImpl")
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

    @Override
    public Admin login(LoginForm loginForm) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername()).eq("password", MD5.encrypt(loginForm.getPassword()));
        Admin admin = baseMapper.selectOne(queryWrapper);
        return admin;
    }

    @Override
    public Admin getAdminById(Long userId) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        Admin admin = baseMapper.selectOne(queryWrapper);
        return admin;
    }

    @Override
    public IPage<Admin> getAdminsByOpr(Page<Admin> pageParam, String adminName) {
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<>();
        if (!StringUtils.isEmpty(adminName)) {
            queryWrapper.like("name",adminName);
        }
        queryWrapper.orderByDesc("id");
        Page<Admin> page = baseMapper.selectPage(pageParam, queryWrapper);
        return page;
    }

    }




