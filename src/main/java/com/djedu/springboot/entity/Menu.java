package com.djedu.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author Dong
 * @since 2022-11-28
 */
@Getter
@Setter
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 图标
     */
    private String icon;

    /**
     * 描述
     */
    private String description;

    /**
     * 父节点ID
     */

    private Integer pid;

    /**
     * 设置子节点集合,但此字段表中不存在
     */
    @TableField(exist = false)
    private List<Menu> children;

    private String pagePath;
}
