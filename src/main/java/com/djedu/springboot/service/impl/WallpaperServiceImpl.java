package com.djedu.springboot.service.impl;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.djedu.springboot.common.Constants;
import com.djedu.springboot.entity.Wallpaper;
import com.djedu.springboot.mapper.WallpaperMapper;
import com.djedu.springboot.service.WallpaperService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 董杰
 * @version 1.0
 */
@Service
public class WallpaperServiceImpl implements WallpaperService {
    @Resource
    private WallpaperMapper wallpaperMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
//    @Cacheable(value = "files", key = "'frontAll'")
    public List<Wallpaper> getAll() {
        //1.从缓存中获取数据
        String jsonStr = stringRedisTemplate.opsForValue().get(Constants.FILES_KEY);
        List<Wallpaper> wallpapers;
        //2. 判断取出来的json是否为空
        if (StrUtil.isBlank(jsonStr)) {
            // 3. 从数据库取出数据
            wallpapers = wallpaperMapper.getAll();
            // 4.再取缓存到redis
            stringRedisTemplate.opsForValue().set(Constants.FILES_KEY, JSONUtil.toJsonStr(wallpapers));
        } else {
            // 减轻数据库的压力
            // 5. 如果有, 从redis缓存中获取数据(将json数据转换成class)
            wallpapers = JSONUtil.toBean(jsonStr, new TypeReference<List<Wallpaper>>() {
            }, true);
        }
        return wallpapers;
    }
}
