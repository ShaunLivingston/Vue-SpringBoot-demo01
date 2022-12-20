package com.djedu.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djedu.springboot.entity.Dict;
import com.djedu.springboot.mapper.DictMapper;
import com.djedu.springboot.service.DictService;
import org.springframework.stereotype.Service;

/**
 * @author 董杰
 * @version 1.0
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
}
