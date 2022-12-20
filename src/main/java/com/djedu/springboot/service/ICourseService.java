package com.djedu.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djedu.springboot.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dong
 * @since 2022-12-13
 */
public interface ICourseService extends IService<Course> {

    public Page<Course> findPage(Page<Course> page, String name);

    void setStuCourse(Integer studentId, Integer courseId);
}
