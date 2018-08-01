package com.example.config;

import com.example.components.LoginHandleInterceptor;
import com.example.components.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 扩展SpringMVC，既保留了SpringBoot自动配置，也能用自己扩展的配置
 * 而SpringBoot的SpringMvc自动配置在WebMvcAutoConfiguration类
 * 和EnableWebMvcConfiguration类中加载和导入全部WebMvc配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 设置视图映射(页面跳转)
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    /**
     * 配置拦截器
     * SpringBoot-2.x和Spring5.0的静态资源也会执行自定义的拦截器，因此在配置拦截器的时候需要指定排除静态资源的访问路径
     * 因此配置拦截器的时候还要拦截静态资源的路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**")    // 拦截全局路径请求，下为放行某些url的请求
                .excludePathPatterns("/", "/index.html", "/user/login", "/static/**", "/error");    // 1. 注意这个/resources/**是我自定义的资源路径 2. 不要拦截需要请求的路径，比如这里的/user/login
//                .excludePathPatterns("/static/**", "/webjars/**", "/resource/**", "/public/**");
    }


    /**
     * 定义静态资源URL
     * 使用了自定义拦截器后SpringBoot2.x的静态资源也会使用这个拦截器，
     * 而且好像屏蔽了自动配置的静态资源Handlers设置，
     * 上面的拦截器移除似乎需要这个方法进行解释路径url，之后才能在拦截器里才能使用这些url。
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*
         * 在这里注册了一个静态资源url为/static/**，但是这样的话，每个静态资源前面都需要加上/static/前缀
         */
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/META-INF/resources/", "classpath:/static/", "classpath:/resources/", "classpath:/public/");

//         String [] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("classpath:/resources/");
//        registry.addResourceHandler("/public/**")
//                .addResourceLocations("classpath:/public/");

    }

    /**
     * 配置LocaleResolver实现自定义国际化功能
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

}