package com.djedu.springboot.controller.dto;

import com.djedu.springboot.entity.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author 董杰
 * @version 1.0
 */

/**
 * 接收前端登录请求的参数
 */
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String token;
    private String role;
    private List<Menu> menus;
}
