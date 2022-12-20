package com.djedu.springboot.controller;

import com.djedu.springboot.common.Result;
import com.djedu.springboot.config.AuthAccess;
import com.djedu.springboot.service.WallpaperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 董杰
 * @version 1.0
 */
@RestController
@RequestMapping("/wallpaper")
public class WallpaperController {
    @Resource
    private WallpaperService wallpaperService;

    @AuthAccess
    @GetMapping("/front/all")
    public Result getAll() {
        return Result.success(wallpaperService.getAll());
    }
}
