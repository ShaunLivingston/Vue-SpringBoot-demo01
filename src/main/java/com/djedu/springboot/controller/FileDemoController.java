package com.djedu.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djedu.springboot.common.Constants;
import com.djedu.springboot.common.Result;
import com.djedu.springboot.entity.FileDemo;
import com.djedu.springboot.mapper.FileDemoMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author 董杰
 * @version 1.0
 */

/**
 * 文件上传相关接口
 */
@RestController
@RequestMapping("/file")
public class FileDemoController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Value("${server.ip}")
    private String serverIp;

    @Resource
    private FileDemoMapper fileDemoMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 文件上传接口
     *
     * @param file
     * @return
     * @throws IOException
     */

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
        //先存储到磁盘
        File uploadParentFile = new File(fileUploadPath);
        //判断配置的文件目录是否存在，若不存在则创建一个文件目录
        if (!uploadParentFile.exists()) {
            uploadParentFile.mkdirs();
        }
        //定义一个文件唯一的标识码（采用UUID）
        String uuid = IdUtil.fastSimpleUUID();
        //唯一的文件全限定名
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);
        //把获取到的文件存储的到磁盘目录
        file.transferTo(uploadFile);
        String md5 = SecureUtil.md5(uploadFile);
        FileDemo dbFileDemo = getByMd5(md5);
        String url;
        if (dbFileDemo == null) {
            url = "http://" + serverIp + ":9090/file/" + fileUUID;
        } else {
            url = dbFileDemo.getUrl();
            //由于文件已经存在，所以删除刚才上传的重复内容的文件
            uploadFile.delete();
        }
        //存储数据库
        FileDemo fileDemo = new FileDemo();
        fileDemo.setName(originalFilename);
        fileDemo.setType(type);
        fileDemo.setSize(size / 1024);
        fileDemo.setUrl(url);
        fileDemo.setMd5(md5);
        fileDemoMapper.insert(fileDemo);

        // 从redis取出数据，操作完，再设置（不用查询数据库）
        String json = stringRedisTemplate.opsForValue().get(Constants.FILES_KEY);
        List<FileDemo> files1 = JSONUtil.toBean(json, new TypeReference<List<FileDemo>>() {
        }, true);
        files1.add(fileDemo);
        setCache(Constants.FILES_KEY, JSONUtil.toJsonStr(files1));

        // 从数据库查出数据
//        List<Files> files = fileMapper.selectList(null);
//        // 设置最新的缓存
//        setCache(Constants.FILES_KEY, JSONUtil.toJsonStr(files));

        // 最简单的方式：直接清空缓存
//        flushRedis(Constants.FILES_KEY);
        return url;
    }

    /**
     * 文件下载接口："http://localhost:9090/file/{fileUUID}"
     *
     * @param fileUUID
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        //根据文件的唯一标识码进行获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        //设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");
        //读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    /**
     * 判断是否上传的相同内容类型的文字（名字有可能不同）
     *
     * @param md5
     * @return
     */
    public FileDemo getByMd5(String md5) {
        QueryWrapper<FileDemo> fileDemoQueryWrapper = new QueryWrapper<>();
        fileDemoQueryWrapper.eq("md5", md5);
        List<FileDemo> list = fileDemoMapper.selectList(fileDemoQueryWrapper);
        return list.size() == 0 ? null : list.get(0);
    }

    /**
     * 分页
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result findConditionPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String name) {
        IPage<FileDemo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<FileDemo> queryWrapper = new QueryWrapper<>();
        //查询未删除的记录
        queryWrapper.eq("is_delete", false);
        queryWrapper.like(Strings.isNotEmpty(name), "name", name);
        return Result.success(fileDemoMapper.selectPage(page, queryWrapper));
    }

    @DeleteMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        QueryWrapper<FileDemo> fileDemoQueryWrapper = new QueryWrapper<>();
        fileDemoQueryWrapper.in("id", ids);
        List<FileDemo> fileDemos = fileDemoMapper.selectList(fileDemoQueryWrapper);
        for (FileDemo fileDemo : fileDemos) {
            fileDemo.set_delete(true);
            fileDemoMapper.updateById(fileDemo);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        FileDemo fileDemo = fileDemoMapper.selectById(id);
        fileDemo.set_delete(true);
        fileDemoMapper.updateById(fileDemo);
        flushRedis(Constants.FILES_KEY);
        return Result.success();
    }

    /**
     * 文件更新接口
     *
     * @return
     * @paramfiledemo
     */
    @PostMapping("/update")
    public Result update(@RequestBody FileDemo fileDemo) {
        fileDemoMapper.updateById(fileDemo);
        flushRedis(Constants.FILES_KEY);
        return Result.success();
    }

    /**
     * 查询指定的视频集
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public Result getVideoById(@PathVariable Integer id) {
        return Result.success(fileDemoMapper.selectById(id));
    }

    /**
     * 取出后台所有的文件
     *
     * @return
     */
    @GetMapping("/front/all")
    public Result getAllFiles() {
        return Result.success(fileDemoMapper.selectList(null));
    }

    // 设置缓存
    private void setCache(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    // 删除缓存
    private void flushRedis(String key) {
        stringRedisTemplate.delete(key);
    }


}
