package com.djedu.springboot.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djedu.springboot.common.Constants;
import com.djedu.springboot.common.Result;
import com.djedu.springboot.controller.dto.UserDTO;
import com.djedu.springboot.controller.dto.UserPasswordDTO;
import com.djedu.springboot.entity.User;
import com.djedu.springboot.service.IUserService;
import com.djedu.springboot.utils.TokenUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Dong
 * @since 2022-11-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping
    public Result save(@RequestBody User user) {
        return Result.success(userService.saveOrUpdate(user));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.list());
    }

//    @GetMapping("/page")
//    public Page<User> findPage(@RequestParam Integer pageNum,
//                               @RequestParam Integer pageSize) {
//        return userService.page(new Page<>(pageNum, pageSize));
//    }

    @DeleteMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeByIds(ids));
    }

    @GetMapping("/page")
    public Result findConditionPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "") String nickname, @RequestParam(defaultValue = "") String address) {
//        IPage<User> page = new Page<>(pageNum, pageSize);
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(Strings.isNotEmpty(username), "username", username);
//        queryWrapper.like(Strings.isNotEmpty(nickname), "nickname", nickname);
//        queryWrapper.like(Strings.isNotEmpty(address), "address", address);
//        queryWrapper.orderByDesc("id");
        //获取当前用户信息
//        User currentUser = TokenUtils.getCurrentUser();
//        System.out.println(currentUser.getNickname());
        return Result.success(userService.findPage(new Page<>(pageNum, pageSize), username, nickname, address));
    }


    /**
     * 导出接口
     *
     * @param response
     * @throws Exception
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        //从数据库查询出所有的数据
        List<User> list = userService.list();
        //在内存操作，输出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("username", "username");
        writer.addHeaderAlias("password", "password");
        writer.addHeaderAlias("nickname", "nickname");
        writer.addHeaderAlias("email", "email");
        writer.addHeaderAlias("phone", "phone");
        writer.addHeaderAlias("address", "address");
        //一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    /**
     * 导入接口
     */
    @PostMapping("/import")
    public void imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<User> list = reader.readAll(User.class);
        userService.saveBatch(list);
    }

    //验证登录
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_600, "用户名或密码错误...");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    //注册用户
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_600, "用户名或密码错误...");
        }
        return Result.success(userService.register(userDTO));
    }

    @GetMapping("/username/{username}")
    public Result findByUserName(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return Result.success(userService.getOne(queryWrapper));
    }

    /**
     * 修改密码
     *
     * @param userPasswordDTO
     * @return
     */
    @PostMapping("/password")
    public Result password(@RequestBody UserPasswordDTO userPasswordDTO) {
        userService.updatePassword(userPasswordDTO);
        return Result.success();
    }

    /**
     * 查询出教师列表显示在下拉框
     *
     * @param role
     * @return
     */
    @GetMapping("/role/{role}")
    public Result getByRole(@PathVariable String role) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role", role);
        List<User> list = userService.list(queryWrapper);
        return Result.success(list);
    }
}

