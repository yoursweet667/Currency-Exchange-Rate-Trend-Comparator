package ru.yoursweet667.trendсomparator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TrendComparatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrendComparatorApplication.class, args);
	}
}