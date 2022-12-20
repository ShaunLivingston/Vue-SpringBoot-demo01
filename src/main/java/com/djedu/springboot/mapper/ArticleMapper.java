package com.djedu.springboot.mapper;

import com.djedu.springboot.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djedu.springboot.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Dong
 * @since 2022-12-18
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
