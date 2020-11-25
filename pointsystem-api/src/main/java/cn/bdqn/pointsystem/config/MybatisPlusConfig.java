package cn.bdqn.pointsystem.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname MybatisPlusConfig
 * @Description TODO
 * @Date 2019/12/30 16:21
 * @Created by Administrator
 */
        @Configuration
    public class MybatisPlusConfig {

        @Bean
        public PaginationInterceptor paginationInterceptor() {
            return new PaginationInterceptor();
        }
}
