package dev.bbs.study.csw.configs;

import dev.bbs.study.csw.interceptors.AutoSignInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final AutoSignInterceptor autoSignInterceptor;

    @Autowired
    public WebMvcConfig(AutoSignInterceptor autoSignInterceptor) {
        this.autoSignInterceptor = autoSignInterceptor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/**")
                .addResourceLocations("/WEB-INF/resource/");
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.autoSignInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**.css", "/**.png", "/**.jpg", "/**.js");
    }
}