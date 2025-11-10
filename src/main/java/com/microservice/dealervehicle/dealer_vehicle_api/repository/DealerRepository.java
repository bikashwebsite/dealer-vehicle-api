package com.microservice.dealervehicle.dealer_vehicle_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.dealervehicle.dealer_vehicle_api.model.Dealer;

public interface DealerRepository extends JpaRepository<Dealer, Long>{

}
