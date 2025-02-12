package com.chinaclear.sz.component.common;


public interface ConfigurableListableBeanFactory extends BeanFactory {
    /**
     *         //Bean的生命周期
     *         //1. 实例化前；
     *         //2. 实例化Bean
     *         //3. 实例化后
     *         // 循环依赖下的AOP动态代理
     *         //4. 自动注入
     *         // 处理Aware回调
     *         //5. 初始化前
     *         //6. 初始化
     *         //7. 初始化后
     *         //8. 所有单例Bean创建结束
     */
    void preInstantiateSingletons();
}
