package com.dkd.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by King on 2018/9/26.
 */
@Component
@ConfigurationProperties()
@PropertySource("classpath:/properties/constants.properties")
public class Constants {
    public long cacheTime;
    public long stateTime;
    public int countTime;

    public int getCountTime() {
        return countTime;
    }

    public void setCountTime(int countTime) {
        this.countTime = countTime;
    }

    public long getStateTime() {
        return stateTime;
    }

    public void setStateTime(long stateTime) {
        this.stateTime = stateTime;
    }

    public long getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
    }
}
