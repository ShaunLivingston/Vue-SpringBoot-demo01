package com.djedu.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djedu.springboot.entity.Comment;
import com.djedu.springboot.mapper.CommentMapper;
import com.djedu.springboot.service.ICommentService;
import com.djedu.springboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dong
 * @since 2022-12-19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Resource
    private CommentMapper commentMapper;

    public Comment saveComment(Comment comment) {
        if (comment.getId() == null) { // 新增评论
            comment.setUserId(TokenUtils.getCurrentUser().getId());
            comment.setTime(DateUtil.now());
            if (comment.getPid() != null) {  // 判断如果是回复，进行处理
                Integer pid = comment.getPid();
                Comment pComment = commentMapper.selectById(pid);
                if (pComment.getOriginId() != null) {  // 如果当前回复的父级有祖宗，那么就设置相同的祖宗
                    comment.setOriginId(pComment.getOriginId());
                } else {  // 否则就设置父级为当前回复的祖宗
                    comment.setOriginId(comment.getPid());
                }
            }
        }
        return comment;
    }

    @Override
    public List<Comment> findCommentDetail(Integer articleId) {
        return commentMapper.findCommentDetail(articleId);
    }

    @Override
    public List<Comment> findTree(Integer articleId) {
        //1.查询所有的评论和回复数据
        List<Comment> articleComments = commentMapper.findCommentDetail(articleId);

        //2. 查询评论数据（不包括回复）
        List<Comment> originList = articleComments.stream().filter(comment -> comment.getOriginId() == null).collect(Collectors.toList());

        //3. 设置评论数据的子节点，也就是回复内容
        for (Comment origin : originList) {
            // 表示回复对象集合
            List<Comment> comments = articleComments.stream().filter(comment -> origin.getId().equals(comment.getOriginId())).collect(Collectors.toList());
            comments.forEach(comment -> {
                // 找到当前评论的父级
                Optional<Comment> pComment = articleComments.stream().filter(c1 -> c1.getId().equals(comment.getPid())).findFirst();
                // 找到父级评论的用户id和用户昵称，并设置给当前的回复对象
                pComment.ifPresent((v -> {
                    comment.setPuserId(v.getUserId());
                    comment.setPuserNickname(v.getNickname());
                    commentMapper.updateById(comment);
                }));
            });
            origin.setChildren(comments);
        }
        return originList;
    }
}
