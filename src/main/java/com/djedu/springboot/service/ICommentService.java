package com.djedu.springboot.service;

import com.djedu.springboot.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dong
 * @since 2022-12-19
 */
public interface ICommentService extends IService<Comment> {
    public Comment saveComment(Comment comment);

    public List<Comment> findCommentDetail(Integer articleId);

    public List<Comment> findTree(Integer articleId);
}
