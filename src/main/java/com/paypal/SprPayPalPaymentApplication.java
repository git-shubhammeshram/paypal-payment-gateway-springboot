package com.paypal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.paypal.config.PayPalProperties;

@SpringBootApplication
@EnableConfigurationProperties(PayPalProperties.class)
public class SprPayPalPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprPayPalPaymentApplication.class, args);
	}

}
