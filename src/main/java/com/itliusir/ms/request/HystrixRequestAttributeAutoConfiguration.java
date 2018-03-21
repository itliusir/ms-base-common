package com.itliusir.ms.request;

import com.netflix.hystrix.Hystrix;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 实例化RequestAttributeHystrixConcurrencyStrategy
 * 增加配置开关
 *
 * @author liugang
 * @since 2018-01-16
 */
@Configuration
@ConditionalOnClass({Hystrix.class})
@ConditionalOnProperty(value = "hystrix.propagate.request-attribute.enabled", matchIfMissing = false)
@EnableConfigurationProperties(HystrixRequestAttributeProperties.class)
public class HystrixRequestAttributeAutoConfiguration {

    @Bean
    public RequestAttributeHystrixConcurrencyStrategy hystrixRequestAutoConfiguration(){
        return new RequestAttributeHystrixConcurrencyStrategy();
    }
}
