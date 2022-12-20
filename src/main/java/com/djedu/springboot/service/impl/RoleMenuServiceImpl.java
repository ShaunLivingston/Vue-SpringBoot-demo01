package com.djedu.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djedu.springboot.entity.RoleMenu;
import com.djedu.springboot.mapper.RoleMenuMapper;
import com.djedu.springboot.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @author 董杰
 * @version 1.0
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
}
