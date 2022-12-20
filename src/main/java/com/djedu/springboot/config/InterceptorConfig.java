package com.djedu.springboot.config;

import com.djedu.springboot.config.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 董杰
 * @version 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")//拦截所有请求，通过判断token是否合法来决定是否需要登录
                .excludePathPatterns("/user/login","/user/register","/**/export","/**/import","/file/**");//除了这些路径其它路径均要拦截
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
