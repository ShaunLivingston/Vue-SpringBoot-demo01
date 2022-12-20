package com.djedu.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djedu.springboot.controller.dto.UserPasswordDTO;
import com.djedu.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Dong
 * @since 2022-11-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Update("update sys_user set password = #{newPwd} where username = #{username} and password = #{original}")
    int updatePassword(UserPasswordDTO userPasswordDTO);

    Page<User> findPage(Page<User> page, @Param("username") String username, @Param("nickname") String nickname, @Param("address") String address);
}
