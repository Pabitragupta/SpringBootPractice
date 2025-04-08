package com.example.journal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}
}




//To configure the transaction we create a bean, but in mysql the configuration is automatically created
////PlatformTransactionManager
//And also use the class which had all the transaction
////DataSourceTransactionManager