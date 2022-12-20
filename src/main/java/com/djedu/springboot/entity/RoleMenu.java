package com.djedu.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 董杰
 * @version 1.0
 */
@TableName("sys_role_menu")
@Data
public class RoleMenu {
    private Integer roleId;
    private Integer menuId;
}
