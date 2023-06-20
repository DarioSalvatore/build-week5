package app.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Address;
import app.payloads.AddressPayload;
import app.repositories.AddressesRepository;
import app.services.AddressesService;

@RestController
@RequestMapping("/addresses")
public class AddressesController {

	@Autowired
	private AddressesService addressesService;
	@Autowired
	private AddressesRepository addressesRepo;

	@GetMapping("/bills")
	public Page<Address> getAllAddresses(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "name") String sortBy) {
		return addressesService.findAll(page, size, sortBy);
	}

	@GetMapping("/{id}")
	public Address getById(@PathVariable UUID id) throws Exception {
		return addressesService.findById(id);
	}

	@PostMapping("")
	public Address saveAddress(@RequestBody AddressPayload body) {
		Address address = addressesService.create(body);
		return addressesRepo.save(address);
	}

	@PutMapping("/{id}")
	public void findByIdAndUpdate(@PathVariable UUID id, @RequestBody AddressPayload body) {
		try {
			addressesService.findByIdAndUpdate(id, body);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DeleteMapping("/{id}")
	public void deleteAddress(@PathVariable UUID id) {
		addressesService.deleteAddress(id);
	}
}
