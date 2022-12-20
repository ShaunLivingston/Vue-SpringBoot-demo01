package com.djedu.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 *
 * </p>
 *
 * @author Dong
 * @since 2022-11-22
 */
@Getter
@Setter
@TableName("sys_user")
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 角色
     */
    private String role;

    @TableField(exist = false)
    private List<Course> courses;

    @TableField(exist = false)
    private List<Course> stuCourses;
}
