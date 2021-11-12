package de.hska.iwi.eshop_products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan("de.hska.iwi.eshop_products")
@EnableAutoConfiguration
@EnableJpaRepositories("de.hska.iwi.eshop_products")
public class EshopProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopProductsApplication.class, args);
	}

}
