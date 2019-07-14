package br.com.samuel.dito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
public class TesteDitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteDitoApplication.class, args);
	}

}
