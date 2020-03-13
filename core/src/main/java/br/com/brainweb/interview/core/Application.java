package br.com.brainweb.interview.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="br.com.brainweb.interview.core.repository")
@EntityScan("br.com.brainweb.interview.model")
@ComponentScan({"br.com.brainweb.interview.core"})
@RestController
public class Application extends SpringBootServletInitializer {

	
	@RequestMapping("/")
	public String home() {
		return "Hello Docker World";
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
