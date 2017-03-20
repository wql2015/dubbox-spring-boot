package net.zero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * net.zero", "zero.common"解决不同模块依赖组件扫描失败
 * @author unique
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"net.zero.controller", "zero.common.exceptions"})
//@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	// spring-boot提供的解决方案：生成tomcat服务器能管理的war包，而非内嵌的tomcat直接生成的jar包
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
