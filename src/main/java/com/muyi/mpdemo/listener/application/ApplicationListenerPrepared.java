package com.muyi.mpdemo.listener.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
@Slf4j
public class ApplicationListenerPrepared implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        log.error(getClass().getSimpleName());

    }

}
