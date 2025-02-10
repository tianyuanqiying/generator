package com.chinaclear.sz.component.test;

import com.chinaclear.sz.component.anno.Bean;
import com.chinaclear.sz.component.anno.Configuration;

@Configuration
public class ConfigBean {

    @Bean
    public MethodBean methodBean() {
        return new MethodBean();
    }
}
