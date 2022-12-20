package com.djedu.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djedu.springboot.common.Constants;
import com.djedu.springboot.controller.dto.UserDTO;
import com.djedu.springboot.controller.dto.UserPasswordDTO;
import com.djedu.springboot.entity.Menu;
import com.djedu.springboot.entity.User;
import com.djedu.springboot.exception.ServiceException;
import com.djedu.springboot.mapper.RoleMapper;
import com.djedu.springboot.mapper.RoleMenuMapper;
import com.djedu.springboot.mapper.UserMapper;
import com.djedu.springboot.service.IMenuService;
import com.djedu.springboot.service.IUserService;
import com.djedu.springboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dong
 * @since 2022-11-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDTO login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            //防止因为读脏数据而报错
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            userDTO.setMenus(getRoleMenus(one.getRole()));
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误...");
        }
    }

    /**
     * 获取当前角色的菜单列表
     *
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        //当前角色的所有菜单Id集合
        List<Integer> menuIds = roleMenuMapper.selectMenusByRoleId(roleId);
        //查出系统所有的菜单
        List<Menu> menus = menuService.findMenus("");
        //new 一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        //筛选出当前用户角色的父菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
                //筛选出当前角色父菜单包含的子菜单集合
                if (menu.getChildren().size() != 0) {
                    List<Menu> childMenus = menu.getChildren();
                    //removeIf()  移除children里面不在 menuIds集合中的元素
                    childMenus.removeIf(child -> !menuIds.contains(child.getId()));
                }
            }
        }
        return roleMenus;
    }

    //注册
    @Override
    public User register(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        User one;
        try {
            one = getOne(queryWrapper);//从数据库查找是否已存在此用户
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            save(one);//把copy完之后的User对象存储到数据库中
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名已存在...");
        }
        return one;
    }

    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        int update = userMapper.updatePassword(userPasswordDTO);
        if (update < 1) {
            throw new ServiceException(Constants.CODE_600, "密码错误");
        }
    }

    @Override
    public Page<User> findPage(Page<User> page, String username, String nickname, String address) {
        return userMapper.findPage(page, username, nickname, address);
    }
}

