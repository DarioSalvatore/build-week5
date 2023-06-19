package app.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Component;

import app.entities.Address;
import app.repositories.AddressesRepository;

@Component
public class AddressesService {
	@Autowired
	AddressesRepository addressesRepo;

	public Address findById(UUID id) throws NotFoundException {
		return addressesRepo.findById(id)
				.orElseThrow(() -> new app.exceptions.NotFoundException("Indirizzo non trovato!"));
	}
}
