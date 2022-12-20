package com.djedu.springboot.mapper;

import com.djedu.springboot.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Dong
 * @since 2022-11-28
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select id from sys_role where flag = #{roleId}")
    Integer selectByFlag(String roleId);
}
