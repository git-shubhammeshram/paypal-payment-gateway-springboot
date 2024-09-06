package com.paypal.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String payerId;
    private String paymentId;
    private String paymentStatus;
    
    
    
    
	public Payment(String payerId, String paymentId, String paymentStatus) {
		super();
		this.payerId = payerId;
		this.paymentId = paymentId;
		this.paymentStatus = paymentStatus;
	}

    
}
