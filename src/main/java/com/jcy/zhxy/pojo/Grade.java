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
 * @TableName tb_grade
 */
@TableName(value ="tb_grade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableId
    private String name;

    /**
     * 
     */
    private String manager;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String telephone;

    /**
     * 
     */
    private String introducation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}