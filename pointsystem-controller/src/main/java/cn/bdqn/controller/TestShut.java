package cn.bdqn.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: springbootActiveMQ
 * @Package: cn.bdqn.controller
 * @Author: huat
 * @Date: 2020/1/6 15:56
 * @Version: 1.0
 */
@RestController
public class TestShut implements ApplicationContextAware {
    private ApplicationContext context;
    @PostMapping("/shutDown")
    public String shutDownContext() {
        ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) context;
        ctx.close();
        return "context is shutdown";
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
