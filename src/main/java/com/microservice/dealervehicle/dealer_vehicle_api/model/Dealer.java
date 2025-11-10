package com.microservice.dealervehicle.dealer_vehicle_api.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dealer {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private SubscriptionType subscriptionType;

//	public Dealer(Long id, String name, String email, SubscriptionType subscriptionType) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.email = email;
//		this.subscriptionType = subscriptionType;
//	}
	
	

}
