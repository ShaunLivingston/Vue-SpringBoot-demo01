package com.djedu.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djedu.springboot.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Dong
 * @since 2022-12-13
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    Page<Course> findPage(Page<Course> page, String name);

    void setStuCourse(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

    void delStuCourse(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);
}
