package com.microservice.dealervehicle.dealer_vehicle_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.dealervehicle.dealer_vehicle_api.model.Dealer;
import com.microservice.dealervehicle.dealer_vehicle_api.model.Status;
import com.microservice.dealervehicle.dealer_vehicle_api.model.Vehicle;
import com.microservice.dealervehicle.dealer_vehicle_api.repository.DealerRepository;
import com.microservice.dealervehicle.dealer_vehicle_api.repository.VehicleRepository;

import jakarta.annotation.PostConstruct;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepo;
	
	@Autowired
	private DealerRepository dealerRepo;

	private static List<Vehicle> vehicleList = new ArrayList<>();
	
	@PostConstruct
    public void saveInitialData() {

        if (vehicleRepo.count() == 0) {

            List<Dealer> dealers = dealerRepo.findAll();

            // only proceed if 4 dealers exist
            if (dealers.size() >= 4) {
                Dealer dealer1 = dealers.get(0);
                Dealer dealer2 = dealers.get(1);
                Dealer dealer3 = dealers.get(2);
                Dealer dealer4 = dealers.get(3);

                vehicleList.add(new Vehicle(null, "Swift", 700000, Status.AVAILABLE, dealer1));
                vehicleList.add(new Vehicle(null, "Hyundai", 1200000, Status.SOLD, dealer1));
                vehicleList.add(new Vehicle(null, "Honda", 1300000, Status.AVAILABLE, dealer2));
                vehicleList.add(new Vehicle(null, "Mahindra", 1500000,Status.SOLD, dealer3));
                vehicleList.add(new Vehicle(null, "Renault", 600000,Status.AVAILABLE, dealer4));

                vehicleRepo.saveAll(vehicleList);
                System.out.println("Default vehicle data inserted successfully.");
            } else {
                System.out.println("Dealers not found. Skipping vehicle insert.");
            }
        }
    }

	public List<Vehicle> getAllVehicles() {
		
		return vehicleRepo.findAll();
	}

	public Vehicle getVehicleById(long id) {
		
		return vehicleRepo.findById(id).orElse(null);
	}

	public Vehicle addNewVehicle(Vehicle vehicle) {
		
		return vehicleRepo.save(vehicle);
	}

	public Vehicle updateVehicleDeail(Long id, Vehicle vehicle) {
			
			Optional<Vehicle> vehiclebyId = vehicleRepo.findById(id);
			if(vehiclebyId.isPresent()) {
				Vehicle updatedVehicle = vehiclebyId.get();
				updatedVehicle.setModel(vehicle.getModel());;
				updatedVehicle.setPrice(vehicle.getPrice());
				updatedVehicle.setStatus(vehicle.getStatus());
				if (vehicle.getDealer() != null && vehicle.getDealer().getId() != null) {
		            Dealer newDealer = dealerRepo.findById(vehicle.getDealer().getId())
		                .orElseThrow(() -> new RuntimeException("Dealer not found with id: " + vehicle.getDealer().getId()));
		            updatedVehicle.setDealer(newDealer);
		        }

				return vehicleRepo.save(updatedVehicle);
			}
			else {
				throw new RuntimeException("Vehicle not found");
			}
	
		}
	
	public void deleteVehicle(Long id) {
		Optional<Vehicle> vehicle = vehicleRepo.findById(id);
		if(vehicle.isEmpty()) {
			throw new RuntimeException("Vehicle does not exist");
		}
		Vehicle deletedVehicle = vehicle.get();
		vehicleRepo.deleteById(id);	
		
	}
	
	public List<Vehicle> getVehiclesOfPremiumDealers() {
        return vehicleRepo.fetchVehiclesOfPremiumDealer();
    }
	
	
	
}
