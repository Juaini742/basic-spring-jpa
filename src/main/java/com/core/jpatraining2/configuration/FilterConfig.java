package com.core.jpatraining2.configuration;

import com.core.jpatraining2.middleware.TokenAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TokenAuthenticationFilter> tokenFilter() {
        FilterRegistrationBean<TokenAuthenticationFilter> tokenRegistrationBean = new FilterRegistrationBean<>();
        tokenRegistrationBean.setFilter(new TokenAuthenticationFilter());
        tokenRegistrationBean.addUrlPatterns("/*");
        return tokenRegistrationBean;
    }
}
