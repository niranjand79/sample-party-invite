package com.mine.workbench.main;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mine.workbench")
@EnableAutoConfiguration
public class AppMain {

	public static void main(String args[]) throws SQLException {
		SpringApplication.run(AppMain.class, args);
	}
}
