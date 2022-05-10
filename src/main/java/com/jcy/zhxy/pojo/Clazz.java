package com.jcy.zhxy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName tb_clazz
 */
@TableName(value ="tb_clazz")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer number;

    /**
     * 班级介绍
     */
    private String introducation;

    /**
     * 班主任
     */
    private String headmaster;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String telephone;

    /**
     * 年纪
     */
    private String gradeName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}