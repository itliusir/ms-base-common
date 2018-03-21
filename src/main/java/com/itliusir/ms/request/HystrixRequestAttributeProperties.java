package com.itliusir.ms.request;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类
 *
 * @author liugang
 * @since 2018-01-16
 */
@ConfigurationProperties("hystrix.propagate.request-attribute")
public class HystrixRequestAttributeProperties {

    private boolean enabled = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
}
