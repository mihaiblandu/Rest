package com.rest.api.filter.config;

import com.rest.api.filter.RateLimitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimitFilterConfig {
    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilter_Blue(){
        var rateLimitFilter_Blue = new FilterRegistrationBean<RateLimitFilter>();

        rateLimitFilter_Blue.setFilter(new RateLimitFilter(3));
        rateLimitFilter_Blue.setName("rateLimitFilter_Blue");
        rateLimitFilter_Blue.addUrlPatterns("/api/dos/v1/blue");
        return  rateLimitFilter_Blue;
    }

    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilter_Green(){
        var rateLimitFilter_Green = new FilterRegistrationBean<RateLimitFilter>();

        rateLimitFilter_Green.setFilter(new RateLimitFilter(3));
        rateLimitFilter_Green.setName("rateLimitFilter_Green");
        rateLimitFilter_Green.addUrlPatterns("/api/dos/v1/green");
        return  rateLimitFilter_Green;
    }
}
