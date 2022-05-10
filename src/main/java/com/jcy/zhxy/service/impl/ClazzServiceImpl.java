package com.jcy.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcy.zhxy.mapper.ClazzMapper;
import com.jcy.zhxy.pojo.Clazz;
import com.jcy.zhxy.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_clazz】的数据库操作Service实现
* @createDate 2022-05-06 10:19:57
*/
@Service("clazzServiceImpl")
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz>
    implements ClazzService{

    @Override
    public IPage<Clazz> getClazzsByOpr(Page<Clazz> pageParam, Clazz clazz) {
        QueryWrapper<Clazz> queryWrapper=new QueryWrapper<>();
        String gradeName = clazz.getGradeName();
        if (!StringUtils.isEmpty(gradeName)) {
            queryWrapper.like("grade_name",gradeName);
        }

        String name = clazz.getName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name",name);
        }
        queryWrapper.orderByDesc("id");
        Page<Clazz> clazzPage = baseMapper.selectPage(pageParam, queryWrapper);

        return clazzPage;
    }

    @Override
    public List<Clazz> getClazzs() {
        return baseMapper.selectList(null);
    }
}




