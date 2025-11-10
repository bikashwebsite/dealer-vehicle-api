package com.microservice.dealervehicle.dealer_vehicle_api.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.dealervehicle.dealer_vehicle_api.model.Dealer;
import com.microservice.dealervehicle.dealer_vehicle_api.model.SubscriptionType;
import com.microservice.dealervehicle.dealer_vehicle_api.repository.DealerRepository;

import jakarta.annotation.PostConstruct;

@Service
public class DealerService {
	
	@Autowired
	private DealerRepository dealerrepo;
	
	private static List<Dealer> dealerList = new ArrayList<Dealer>();
	
	static {
        dealerList.add(new Dealer(null, "Bikash Motors", "bikashmotors@gmail.com", SubscriptionType.PREMIUM));
        dealerList.add(new Dealer(null, "Speed Cars", "speedcars@gmail.com", SubscriptionType.BASIC));
        dealerList.add(new Dealer(null, "Elite Wheels", "elitewheels@gmail.com", SubscriptionType.PREMIUM));
        dealerList.add(new Dealer(null, "AutoHub", "autohub@gmail.com", SubscriptionType.BASIC));
    }
	
	@PostConstruct
	public void saveInitialData() {
		if(dealerrepo.count() == 0) {
			dealerrepo.saveAll(dealerList);
		}
	}

	public List<Dealer> getAllDealers() {
		
		return dealerrepo.findAll();
	}

	public Dealer getDealerById(long id) {
		
		return dealerrepo.findById(id).orElse(null);
	}

	public Dealer createNewDealer(Dealer dealer) {
		
		return dealerrepo.save(dealer);
	}

	public Dealer updateDealerDeail(Long id, Dealer dealer) {
			
			Optional<Dealer> dealerbyId = dealerrepo.findById(id);
			if(dealerbyId.isPresent()) {
				Dealer updatedDealer = dealerbyId.get();
				updatedDealer.setName(dealer.getName());;
				updatedDealer.setEmail(dealer.getEmail());
				updatedDealer.setSubscriptionType(dealer.getSubscriptionType());
				
				return dealerrepo.save(updatedDealer);
			}
			else {
				throw new RuntimeException("Dealer not found");
			}
	
		}
	
	public void deleteDealer(Long id) {
		Optional<Dealer> dealer = dealerrepo.findById(id);
		if(dealer.isEmpty()) {
			throw new RuntimeException("Dealer does not exist");
		}
		Dealer deletedDeaaler = dealer.get();
		dealerrepo.deleteById(id);
		
		
	}
	
	
}
