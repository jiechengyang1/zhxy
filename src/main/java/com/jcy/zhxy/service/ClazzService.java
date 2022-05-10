package com.jcy.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcy.zhxy.pojo.Clazz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_clazz】的数据库操作Service
* @createDate 2022-05-06 10:19:57
*/
public interface ClazzService extends IService<Clazz> {

    IPage<Clazz> getClazzsByOpr(Page<Clazz> page, Clazz clazz);

    List<Clazz> getClazzs();
}
