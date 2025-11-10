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


import com.microservice.dealervehicle.dealer_vehicle_api.model.Dealer;
import com.microservice.dealervehicle.dealer_vehicle_api.service.DealerService;

@RestController
@RequestMapping("/dealers")
//@CrossOrigin(origins = "http://localhost:9012")
@CrossOrigin(origins = "*")
public class DealerController {
	
	@Autowired
	private DealerService dealerService;
	
	@GetMapping
	public List<Dealer> getDealers(){
		return dealerService.getAllDealers();
	}
	
	@GetMapping("/{id}")
	public Dealer getSpecificDealer(@PathVariable long id) {
		return dealerService.getDealerById(id);
	}
	
	@PostMapping("/addDealer")
	public Dealer addDealer(@RequestBody Dealer dealer) {
		
		return dealerService.createNewDealer(dealer);
	}
	
	@PutMapping("/updateDealer/{id}")
	public Dealer updateDealer(@PathVariable Long id, @RequestBody Dealer dealer) {
		return dealerService.updateDealerDeail(id,dealer);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDealer(@PathVariable Long id){
		dealerService.deleteDealer(id);
		return ResponseEntity.ok("Dealer deleted successfully");
	}

}
