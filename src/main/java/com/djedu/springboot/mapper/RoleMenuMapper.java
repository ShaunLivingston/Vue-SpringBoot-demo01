package com.djedu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djedu.springboot.entity.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 董杰
 * @version 1.0
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    @Delete("delete from sys_role_menu where role_id = #{roleId}")
    int deleteByRoleId(@Param("roleId") Integer roleId);

    @Select("select menu_id from sys_role_menu where role_id = #{roleId}")
    List<Integer> selectMenusByRoleId(@Param("roleId") Integer roleId);
}
