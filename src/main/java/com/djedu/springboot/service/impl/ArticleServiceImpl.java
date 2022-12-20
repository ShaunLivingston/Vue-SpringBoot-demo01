package com.djedu.springboot.service.impl;

import com.djedu.springboot.entity.Article;
import com.djedu.springboot.mapper.ArticleMapper;
import com.djedu.springboot.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Dong
 * @since 2022-12-18
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
