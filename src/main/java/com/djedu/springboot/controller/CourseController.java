package com.djedu.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djedu.springboot.common.Result;
import com.djedu.springboot.entity.Course;
import com.djedu.springboot.service.ICourseService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Dong
 * @since 2022-12-13
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private ICourseService courseService;

    @PostMapping
    public Result save(@RequestBody Course course) {
        courseService.saveOrUpdate(course);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        courseService.removeById(id);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(courseService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(courseService.getById(id));
    }

    @DeleteMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        courseService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        Page<Course> page = courseService.findPage(new Page<>(pageNum, pageSize), name);
        return Result.success(page);
    }

    @Transactional
    @PostMapping("/studentCourse/{studentId}/{courseId}")
    public Result insertStuCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        courseService.setStuCourse(studentId, courseId);
        return Result.success();
    }
}
