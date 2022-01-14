package de.hska.iwi.eshop_categories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan("de.hska.iwi.eshop_categories")
@EnableAutoConfiguration
@ServletComponentScan
@EnableJpaRepositories("de.hska.iwi.eshop_categories")
public class EshopCategoriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopCategoriesApplication.class, args);
	}

}
