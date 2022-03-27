package com.peerlender.lendingengine;

import Repository.UserRepository;
import domain.model.Balance;
import domain.model.Currency;
import domain.model.Money;
import domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EntityScan(basePackages = {"domain.model"})
@ComponentScan(basePackages = {"application.service, application, service, domain.event"})
@EnableJpaRepositories(basePackages = "Repository")

public class LendingengineApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LendingengineApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		User john = new User( "John","john", "b", 27, "software dev", new Balance());
		User phoenix =new User("Phoenix", "joan", "c", 21, "pilot", new Balance());
		john.topUp(new Money(Currency.USD, 150));
		phoenix.topUp(new Money(Currency.USD, 100));
		userRepository.save(john);
		userRepository.save(phoenix);
		userRepository.save(new User( "Henry","henry", "d", 22, "lastma", new Balance()));
	}
}
