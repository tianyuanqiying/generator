package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.common.BeanDefinition;
import com.chinaclear.sz.component.common.BeanDefinitionRegistry;
import com.chinaclear.sz.component.common.BeanDefinitionRegistryPostProcessor;
import com.chinaclear.sz.component.common.BeanFactory;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationClassPostProcessor implements BeanDefinitionRegistryPostProcessor {
    private String basePackage;
    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) {
        //扫描Bean定义
        List<BeanDefinition> beanDefinitionList = new ArrayList<>();

        //扫描@Component注解
        ComponentScanner classPathScanner = new ComponentScanner();
        List<BeanDefinition> beanDefinitions = classPathScanner.scan(basePackage);
        System.out.println(beanDefinitions);
        beanDefinitionList.addAll(beanDefinitions);

        //扫描配置类Bean @Configuration + @Bean
        ConfigurationScanner configurationScanner = new ConfigurationScanner();
        List<BeanDefinition> configurations = configurationScanner.scan(basePackage);
        beanDefinitionList.addAll(configurations);
        List<BeanMethod> beanMethods = configurationScanner.getBeanMethods();
        for (BeanMethod beanMethod : beanMethods) {
            BeanMethodBeanDefinition beanDefinition = BeanMethodBeanDefinition.buildBeanDefinition(beanMethod);
            beanDefinitionList.add(beanDefinition);
        }

        //TODO：扫描bean.xml文件


        //注册Bean定义
        for (BeanDefinition beanDefinition : beanDefinitionList) {
            beanDefinitionRegistry.registerBeanDefinition(beanDefinition.getBeanName(), beanDefinition);
        }


    }

    @Override
    public void postProcessBeanFactory(BeanFactory beanFactory) {

    }

    public static void main(String[] args) {
        ConfigurationClassPostProcessor configurationScanner = new ConfigurationClassPostProcessor();
        configurationScanner.setBasePackage("com");
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        configurationScanner.postProcessBeanDefinitionRegistry(defaultBeanFactory);
        defaultBeanFactory.getBeanDefinition("");
    }
}
