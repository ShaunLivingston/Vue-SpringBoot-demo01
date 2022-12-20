package com.djedu.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 董杰
 * @version 1.0
 */
@Data
@TableName("sys_file")
public class FileDemo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private long size;
    private String url;
    private String md5;
    private boolean is_delete;
    private boolean enable;
}
