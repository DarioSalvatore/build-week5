package app.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Address;
import app.services.AddressesService;

@RestController
@RequestMapping("/addresses")
public class AddressesController {

	@Autowired
	private AddressesService addressesService;

	@GetMapping("/{id}")
	public Address getById(@PathVariable UUID id) throws Exception {
		return addressesService.findById(id);
	}
}
