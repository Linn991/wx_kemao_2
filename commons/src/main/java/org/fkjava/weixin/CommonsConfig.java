package org.fkjava.weixin;

import org.fkjava.weixin.domain.InMessage;
import org.fkjava.weixin.json.JsonRedisSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public interface CommonsConfig extends 
	//命令行运行器，表示程序是一个命令行程序，需要重新run方法来实现程序的初始化
	//使用一个线程等待程序的停止通知
	CommandLineRunner,
	//此接口实现以后，Spring会在销毁的时候自动调用此接口方法
	//用于发送程序的停止通知
	DisposableBean {
	
	public static final Logger LOG = LoggerFactory.getLogger(CommonsConfig.class);
	@Bean
	public default XmlMapper xmlMapper() {
		XmlMapper mapper = new XmlMapper();
		return mapper;
		
	}
	
	@Bean
	public default RedisTemplate<String, ? extends InMessage> inMessageTemplate(
			@Autowired RedisConnectionFactory connectionFactory){
		RedisTemplate<String, ? extends InMessage> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setValueSerializer(new JsonRedisSerializer());
		return template;
	}


	//停止监视器
	public final Object monitor = new Object();

	@Override
	public default void destroy() throws Exception {
		synchronized (monitor) {
			monitor.notify();
		}
	}

	@Override
	public default void run(String... args) throws Exception {
		new Thread(() -> {
			//等待停止通知
			synchronized (monitor) {
				try {
					monitor.wait();//无限期等待
				} catch (InterruptedException e) {
					LOG.error("无法等待停止通知："+e.getLocalizedMessage(),e);
				}
			}
		}).start();
	}

}
