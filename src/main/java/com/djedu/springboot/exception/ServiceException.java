package com.djedu.springboot.exception;

/**
 * @author 董杰
 * @version 1.0
 */

import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
