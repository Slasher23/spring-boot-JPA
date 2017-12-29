package com.commerzbank.bns;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import com.bfwg.repository.UserRepository;
import com.bfwg.service.impl.CustomUserDetailsService;

@SpringBootApplication
@EnableJpaRepositories
public class MySpringBootApplication implements CommandLineRunner {
	@Autowired
	private ServletContext servletContext;

	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(dataSource.getConnection());
		System.out.println("StartXj");
	}

	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}

	@Bean
	public ContentNegotiationConfigurer configurer() {
		return new ContentNegotiationConfigurer(servletContext);
	}

	@Bean
	public CustomUserDetailsService jwtUserDetailsService() {
		return new CustomUserDetailsService();
	}

}
