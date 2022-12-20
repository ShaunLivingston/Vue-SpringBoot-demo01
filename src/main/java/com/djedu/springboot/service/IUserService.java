package com.djedu.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djedu.springboot.controller.dto.UserDTO;
import com.djedu.springboot.controller.dto.UserPasswordDTO;
import com.djedu.springboot.entity.Course;
import com.djedu.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dong
 * @since 2022-11-22
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);

    Page<User> findPage(Page<User> page, String username, String nickname, String address);
}
