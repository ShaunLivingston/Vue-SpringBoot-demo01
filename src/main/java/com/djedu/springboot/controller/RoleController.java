package com.djedu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djedu.springboot.common.Result;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.djedu.springboot.service.IRoleService;
import com.djedu.springboot.entity.Role;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Dong
 * @since 2022-11-28
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @PostMapping
    @CachePut(value = "roles", key = "'role'")
    public Result save(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        roleService.removeById(id);
        return Result.success();
    }

    @GetMapping
    @Cacheable(value = "roles", key = "'role'")
    public Result findAll() {
        return Result.success(roleService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(roleService.getById(id));
    }

    @DeleteMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        roleService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String name) {
        IPage<Role> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return Result.success(roleService.page(page, queryWrapper));
    }

    @PostMapping("/roleMenu/{roleId}")
    public Result roleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(roleId, menuIds);
        return Result.success();
    }

    @GetMapping("/roleMenu/{roleId}")
    public Result getRoleMenu(@PathVariable Integer roleId) {
        return Result.success(roleService.getRoleMenu(roleId));
    }
}
