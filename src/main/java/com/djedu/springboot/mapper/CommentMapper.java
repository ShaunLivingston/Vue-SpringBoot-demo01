package com.djedu.springboot.mapper;

import com.djedu.springboot.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Dong
 * @since 2022-12-19
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select c.*,u.nickname,avatar from sys_comment c left join sys_user u on c.user_id = u.id " +
            " where c.article_id = #{articleId} order by id desc")
    public List<Comment> findCommentDetail(@Param("articleId") Integer articleId);
}
