package kr.ac.jbnu.guestbook.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages= {"kr.ac.jbnu.guestbook.dao","kr.ac.jbnu.guestbook.service"})
@Import(DBConfig.class)
public class ApplicationConfig {

}
