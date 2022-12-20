package com.djedu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.djedu.springboot.common.Constants;
import com.djedu.springboot.common.Result;
import com.djedu.springboot.entity.Dict;
import com.djedu.springboot.entity.Menu;
import com.djedu.springboot.service.DictService;
import com.djedu.springboot.service.IMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Dong
 * @since 2022-11-28
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @Resource
    private DictService dictService;

    @PostMapping
    public Result save(@RequestBody Menu menu) {
        menuService.saveOrUpdate(menu);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        menuService.removeById(id);
        return Result.success();
    }

    @GetMapping
    public Result findAll(@RequestParam(defaultValue = "") String name) {
        return Result.success(menuService.findMenus(name));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(menuService.getById(id));
    }

    @DeleteMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        menuService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping("/ids")
    public Result findAllIds() {
        return Result.success(menuService.list().stream().map(Menu::getId));
    }

    @GetMapping("/icons")
    public Result getIcons() {
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictService.list(dictQueryWrapper));
    }
}
