package com.djedu.springboot.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djedu.springboot.common.Result;
import com.djedu.springboot.utils.TokenUtils;
import jdk.nashorn.internal.parser.Token;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.djedu.springboot.service.ICommentService;
import com.djedu.springboot.entity.Comment;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Dong
 * @since 2022-12-19
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private ICommentService commentService;

    /**
     * 新增评论
     *
     * @param comment
     * @return
     */
    @PostMapping
    public Result saveComment(@RequestBody Comment comment) {
        Comment pushComment = commentService.saveComment(comment);
        commentService.saveOrUpdate(pushComment);
        return Result.success();
    }

    /**
     * 查询评论的子评论集合
     * @param articleId
     * @return
     */
    @GetMapping("/tree/{articleId}")
    public Result findTree(@PathVariable Integer articleId) {
        return Result.success(commentService.findTree(articleId));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        commentService.removeById(id);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(commentService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(commentService.getById(id));
    }

    @DeleteMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        commentService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        IPage<Comment> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(commentService.page(page, queryWrapper));
    }
}
