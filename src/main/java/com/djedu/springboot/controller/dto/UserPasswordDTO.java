package com.djedu.springboot.controller.dto;

import lombok.Data;

/**
 * @author 董杰
 * @version 1.0
 */
@Data
public class UserPasswordDTO {
    private String username;
    private String original;
    private String newPwd;
}
