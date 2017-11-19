package com.ralap.chat.config;

import javax.validation.groups.ConvertGroup;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ralap on 2017/11/12.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/home");
        registry.addViewController("/home").setViewName("/home");
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/pointChat").setViewName("/point_chat");
        registry.addViewController("/groupChat").setViewName("/group_chat");

    }
}
