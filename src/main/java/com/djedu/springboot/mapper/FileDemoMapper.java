package com.djedu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djedu.springboot.entity.FileDemo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 董杰
 * @version 1.0
 */
@Mapper
public interface FileDemoMapper extends BaseMapper<FileDemo> {
}
