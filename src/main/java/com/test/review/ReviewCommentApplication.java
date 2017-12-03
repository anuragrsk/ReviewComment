package com.test.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.test.review.config.EnableSwagger;
import com.test.review.config.MvcConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableCaching
@EnableTransactionManagement
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"com.test.review"})
@Import({EnableSwagger.class ,MvcConfig.class})
@EnableScheduling
public class ReviewCommentApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(ReviewCommentApplication.class, args);
	}
	/**
	 * enable to deploy in web container 
	 */
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ReviewCommentApplication.class);
    }
}
