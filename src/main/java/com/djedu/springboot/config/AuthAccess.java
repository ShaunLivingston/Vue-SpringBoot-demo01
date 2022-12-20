package com.djedu.springboot.config;

import java.lang.annotation.*;

/**
 * @author 董杰
 * @version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAccess  {
}
