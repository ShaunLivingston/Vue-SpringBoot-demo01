package com.djedu.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djedu.springboot.entity.Course;
import com.djedu.springboot.mapper.CourseMapper;
import com.djedu.springboot.service.ICourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dong
 * @since 2022-12-13
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Resource
    private CourseMapper courseMapper;

    @Override
    public Page<Course> findPage(Page<Course> page, String name) {
        return courseMapper.findPage(page, name);
    }

    @Override
    public void setStuCourse(Integer studentId, Integer courseId) {
        courseMapper.delStuCourse(studentId, courseId);
        courseMapper.setStuCourse(studentId, courseId);
    }
}
