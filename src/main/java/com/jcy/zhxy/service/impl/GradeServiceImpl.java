package com.jcy.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcy.zhxy.pojo.Grade;
import com.jcy.zhxy.service.GradeService;
import com.jcy.zhxy.mapper.GradeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_grade】的数据库操作Service实现
* @createDate 2022-05-06 10:20:01
*/
@Service("gradeServiceImpl")
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade>
    implements GradeService{

    @Override
    public IPage<Grade> getGradeByOpr(Page<Grade> page, String gradeName) {
        QueryWrapper<Grade> queryWrapper =new QueryWrapper<>();
        if (!StringUtils.isEmpty(gradeName)) {
            queryWrapper.like("name",gradeName);
        }
        queryWrapper.orderByDesc("id");
        Page<Grade> page1 = baseMapper.selectPage(page, queryWrapper);
        return page1;

    }

    @Override
    public List<Grade> getGrades() {
       return baseMapper.selectList(null);
    }
}




