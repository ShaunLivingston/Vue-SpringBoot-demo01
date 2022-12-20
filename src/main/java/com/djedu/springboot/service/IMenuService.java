package com.djedu.springboot.service;

import com.djedu.springboot.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Dong
 * @since 2022-11-28
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
