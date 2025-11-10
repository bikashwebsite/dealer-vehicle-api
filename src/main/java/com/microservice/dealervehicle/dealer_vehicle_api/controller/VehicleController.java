package com.microservice.dealervehicle.dealer_vehicle_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.dealervehicle.dealer_vehicle_api.model.Vehicle;
import com.microservice.dealervehicle.dealer_vehicle_api.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins = "http://localhost:9012")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping
	public List<Vehicle> getVehicles(){
		return vehicleService.getAllVehicles();
	}
	
	@GetMapping("/{id}")
	public Vehicle getSpecificVehicle(@PathVariable long id) {
		return vehicleService.getVehicleById(id);
	}
	
	@PostMapping("/addVehicle")
	public Vehicle addVechicle(@RequestBody Vehicle vehicle) {
		
		return vehicleService.addNewVehicle(vehicle);
	}
	
	@PutMapping("/updateVehicle/{id}")
	public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
		return vehicleService.updateVehicleDeail(id,vehicle);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDealer(@PathVariable Long id){
		vehicleService.deleteVehicle(id);
		return ResponseEntity.ok("Vehicle deleted successfully");
	}
	
	@GetMapping("/premium")
    public ResponseEntity<List<Vehicle>> getVehiclesOfPremiumDealers() {
        return ResponseEntity.ok(vehicleService.getVehiclesOfPremiumDealers());
    }

}
