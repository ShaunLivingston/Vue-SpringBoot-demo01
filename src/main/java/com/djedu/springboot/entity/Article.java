package com.djedu.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author Dong
 * @since 2022-12-18
 */
@Getter
@Setter
@TableName("sys_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String header;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布人
     */
    private String user;

    /**
     * 最新发布时间
     */
    private String time;


}
