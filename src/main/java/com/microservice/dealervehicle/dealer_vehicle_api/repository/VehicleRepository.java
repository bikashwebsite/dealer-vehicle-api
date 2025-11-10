package com.microservice.dealervehicle.dealer_vehicle_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.microservice.dealervehicle.dealer_vehicle_api.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

	@Query("SELECT v from Vehicle v where v.dealer.subscriptionType = 'PREMIUM'")
	List<Vehicle> fetchVehiclesOfPremiumDealer();
}
