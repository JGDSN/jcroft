package de.agdsn.jcroft;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.GzipResourceResolver;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
public class MVCConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/res/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(3600)
                .setCacheControl(CacheControl.maxAge(3600, TimeUnit.SECONDS))
                .resourceChain(true)
                .addResolver(new GzipResourceResolver());
    }
}