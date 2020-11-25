package cn.bdqn.component;

import cn.bdqn.pointsystem.utlis.FileUpload;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author yajun
 * @create 2019/12/27
 */

/**
 * 解决fastjson的中文乱码和拦截器，初始网页跳转
 * @author yajun
 * @create 2019/12/19
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    LoginHandlerInterceptor loginHandlerInterceptor;
    @Autowired
    LoginSystemHandlerInterceptor loginSystemHandlerInterceptor;
    @Autowired
    StudentHandlerInterceptor studentHandlerInterceptor;
    @Autowired
    AdminHandlerInterceptor adminHandlerInterceptor;

    /**
     * 解决json中文乱码
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter(){
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
    @Bean
    LoginHandlerInterceptor getLoginHandlerInterceptor(){
        return new LoginHandlerInterceptor();
    }

    @Bean
    LoginSystemHandlerInterceptor getLoginSystemHandlerInterceptor(){
        return new LoginSystemHandlerInterceptor();
    }

    @Bean
    AdminHandlerInterceptor getAdminHandlerInterceptor(){
        return new AdminHandlerInterceptor();
    }

    @Bean
    StudentHandlerInterceptor getStudentHandlerInterceptor(){
        return new StudentHandlerInterceptor();
    }

    @Bean
    public MappingJackson2HttpMessageConverter messageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(getObjectMapper());
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.add(responseBodyConverter());
        //解决 添加解决中文乱码后 上述配置之后，返回json数据直接报错 500：no convertter for return value of type
        converters.add(messageConverter());
    }


    @Value("${value.systemPath}")
    private String systemPath;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/**");
        registry.addResourceHandler("/bdqn/**").addResourceLocations(FileUpload.getpath() +systemPath);
    }

//        String  outUrl =null;
//        try{
//            //获得项目路径：file:/D:/Document/Document.jar!/BOOT-INF/classes!/static
//            outUrl = ResourceUtils.getURL("classpath:static").getPath().replace("%20", " ");
//
//        }catch (Exception e){
//
//        }
//        //拿到*.jar!/在路径中的位置
//        int result1=outUrl.lastIndexOf("pointsystem.jar!/");
//        //截取掉后面的
//        outUrl = outUrl.substring(0,result1)+"upload/";
//        //输出结果为file:/D:/Document/upload/
//        System.out.println(outUrl);
//
//        //将file:/D:/Document/upload/设置为/upload/**
//        //注意虚拟路径设置时不能将file:/去掉
//        registry.addResourceHandler("/upload/**").addResourceLocations(outUrl);
//    }

    /**
     * 默认跳转页面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/adminIndex").setViewName("admin/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/student/**").
                excludePathPatterns("/","/student/user/toErrorLogin","/jump/loginTeacher","/student/user/login", "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg",
                        "/**/*.jpeg", "/**/*.gif", "/**/*.svg","/**/*.ico","/**/*.map","classpath:/static/");
        registry.addInterceptor(loginSystemHandlerInterceptor).addPathPatterns("/sys/**","/jump/**").
                excludePathPatterns("/","/student/user/toErrorLogin","/jump/loginTeacher","/student/user/login",
                        "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/*.gif",
                        "/**/*.svg","/**/*.ico","/**/*.map","classpath:/static/","/sys/login","/sys/toErrorLogin");
        registry.addInterceptor(adminHandlerInterceptor).addPathPatterns("/sys/**","/jump/**").
                excludePathPatterns("/","/student/user/toErrorLogin","/jump/loginTeacher","/student/user/login",
                        "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/*.gif",
                        "/**/*.svg","/**/*.ico","/**/*.map","classpath:/static/","/sys/login","/sys/toErrorLogin");
        registry.addInterceptor(studentHandlerInterceptor).addPathPatterns("/student/**").
                excludePathPatterns("/","/student/user/toErrorLogin","/jump/loginTeacher","/student/user/login", "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg",
                        "/**/*.jpeg", "/**/*.gif", "/**/*.svg","/**/*.ico","/**/*.map","classpath:/static/");
    }
}

