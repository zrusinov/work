package com.zdravko;

import com.zdravko.models.Customer;
import com.zdravko.models.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);

	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
            log.info("ZDRAVKO Application started!!!");
			// save a couple of customers
			/*repository.save(new Customer("Jack", "Bauer", "jack.bauer@sap.com"));
			repository.save(new Customer("Chloe", "O'Brian", "chloe.obrian@sap.com"));
			repository.save(new Customer("Kim", "Bauer", "kim.bauer@sap.com"));
			repository.save(new Customer("David", "Palmer", "david.palmer@sap.com"));
			repository.save(new Customer("Michelle", "Dessler", "michelle.dessler@sap.com"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

            log.info("Customers found with findByLastName():");
			// fetch customers by last name
			List<Customer> list = repository.findByLastName("Bauer");

			for(Customer customer : list) {
				log.info("Customer found with findByLastName():");
				log.info("--------------------------------");
				log.info(customer.toString());
				log.info("");
			}

			log.info("");*/
		};
	}

}
