package com.muyi.mpdemo;

import com.muyi.mpdemo.listener.application.ApplicationListenerEnvironmentPrepared;
import com.muyi.mpdemo.listener.application.ApplicationListenerFailed;
import com.muyi.mpdemo.listener.application.ApplicationListenerPrepared;
import com.muyi.mpdemo.listener.application.ApplicationListenerStarted;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
@MapperScan(basePackages = "com.muyi.mpdemo.dao")
public class MpdemoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MpdemoApplication.class);

		/**
		 * 增加application event 监听
		app.addListeners(new ApplicationListenerEnvironmentPrepared());
		app.addListeners(new ApplicationListenerFailed());
		app.addListeners(new ApplicationListenerStarted());
		app.addListeners(new ApplicationListenerPrepared());
		 */

		app.run(MpdemoApplication.class, args);
	}
}

