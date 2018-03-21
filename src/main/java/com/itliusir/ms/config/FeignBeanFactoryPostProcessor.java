package com.itliusir.ms.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 如使用feign遇到test测试结束报:
 * org.springframework.beans.factory.BeanCreationNotAllowedException:
 * Error creating bean with name 'eurekaAutoServiceRegistration':
 * Singleton bean...Do not request a bean from a BeanFactory in a destroy method implementation!
 * 可以加上该配置
 *
 * @author liugang
 * @since  2017-12-12 15:12:33
 */
@Component
public class FeignBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition bd = beanFactory.getBeanDefinition("feignContext");
        bd.setDependsOn("eurekaServiceRegistry", "inetUtils");
    }
}