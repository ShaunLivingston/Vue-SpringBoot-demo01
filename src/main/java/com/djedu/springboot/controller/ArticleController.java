package com.djedu.springboot.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djedu.springboot.common.Result;
import com.djedu.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.djedu.springboot.service.IArticleService;
import com.djedu.springboot.entity.Article;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Dong
 * @since 2022-12-18
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private IArticleService articleService;

    @PostMapping
    public Result save(@RequestBody Article article) {
        if (article.getId() == null) {
            article.setTime(DateUtil.now());  //相当于new Date()
            article.setUser(TokenUtils.getCurrentUser().getNickname());
        }
        articleService.saveOrUpdate(article);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        articleService.removeById(id);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(articleService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(articleService.getById(id));
    }

    @DeleteMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        articleService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String header) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(header)) {
            queryWrapper = queryWrapper.like("header", header);
        }
        return Result.success(articleService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
