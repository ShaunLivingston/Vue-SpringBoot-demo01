package com.djedu.springboot.mapper;

import com.djedu.springboot.entity.Wallpaper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 董杰
 * @version 1.0
 */
@Mapper
public interface WallpaperMapper {
    @Select("select * from sys_wallpaper")
    public List<Wallpaper> getAll();
}
