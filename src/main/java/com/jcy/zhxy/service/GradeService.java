package com.jcy.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcy.zhxy.pojo.Grade;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_grade】的数据库操作Service
* @createDate 2022-05-06 10:20:01
*/
public interface GradeService extends IService<Grade> {

    IPage<Grade> getGradeByOpr(Page<Grade> page, String gradeName);

    List<Grade> getGrades();
}
