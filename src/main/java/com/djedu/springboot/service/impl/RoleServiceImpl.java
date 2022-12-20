package com.djedu.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.djedu.springboot.entity.Menu;
import com.djedu.springboot.entity.Role;
import com.djedu.springboot.entity.RoleMenu;
import com.djedu.springboot.mapper.RoleMapper;
import com.djedu.springboot.mapper.RoleMenuMapper;
import com.djedu.springboot.service.IMenuService;
import com.djedu.springboot.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dong
 * @since 2022-11-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;


    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
//        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("role_id", roleId);
//        roleMenuMapper.delete(queryWrapper);
        //1.先删除当前角色id绑定的所有menu关系
        roleMenuMapper.deleteByRoleId(roleId);
        //2.再将前端传过来的菜单id数组绑定到当前这个角色的id上去
        List<Integer> menuIdsCopy = CollUtil.newArrayList(menuIds);
        for (Integer menuId : menuIds) {
            Menu menu = menuService.getById(menuId);
            //二级菜单 并且传过来的menuId数组里面没有它父级id
            if (menu.getPid() != null && !menuIdsCopy.contains(menu.getPid())) {
                //补上这个父级id
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menu.getPid());
                roleMenuMapper.insert(roleMenu);
                menuIdsCopy.add(menu.getPid());
            }
            //由于数据表sys_role_menu存储的是 单个id 对 id 的关系，因此每传一个菜单id 都要 new 一个RoleMenu对象
            RoleMenu roleMenu = new RoleMenu() ;
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        return roleMenuMapper.selectMenusByRoleId(roleId);
    }
}
