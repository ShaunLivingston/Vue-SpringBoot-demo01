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
 * @since 2022-12-19
 */
@Getter
@Setter
@TableName("sys_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 内容
     */
    private String content;

    /**
     * 评论人id
     */
    private Integer userId;

    /**
     * 评论时间
     */
    private String time;

    /**
     * 父级评论(回复的评论)id
     */
    private Integer pid;

    /**
     * 父级评论(回复的评论)用户id
     */
    private Integer puserId;

    /**
     * 父级评论(回复的评论)用户昵称
     */
    private String puserNickname;

    /**
     * 最上级评论id
     */
    private Integer originId;

    /**
     * 关联文章id
     */
    private Integer articleId;

    /**
     * 发表评论的用户昵称
     */
    @TableField(exist = false)
    private String nickname;

    /**
     * 发表评论的用户头像链接
     */
    @TableField(exist = false)
    private String avatar;

    /**
     * 在此评论下方发表评论的子评论集合
     */
    @TableField(exist = false)
    private List<Comment> children;
}
