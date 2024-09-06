package com.paypal.config;


import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPalConfig {

    @Autowired
    private PayPalProperties payPalProperties;

    @Bean
    public APIContext apiContext() {
        String clientId = payPalProperties.getClientId();
        String clientSecret = payPalProperties.getClientSecret();
        String mode = payPalProperties.getMode();

        // Ensure the mode is either 'sandbox' or 'live'
        if (!"sandbox".equalsIgnoreCase(mode) && !"live".equalsIgnoreCase(mode)) {
            throw new IllegalArgumentException("Mode needs to be either `sandbox` or `live`.");
        }

        return new APIContext(clientId, clientSecret, mode);
    }
}
