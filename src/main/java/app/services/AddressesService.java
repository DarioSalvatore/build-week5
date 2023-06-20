package app.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Component;

import app.entities.Address;
import app.payloads.AddressPayload;
import app.repositories.AddressesRepository;

@Component
public class AddressesService {
	@Autowired
	AddressesRepository addressesRepo;

	public Address findById(UUID id) throws NotFoundException {
		return addressesRepo.findById(id)
				.orElseThrow(() -> new app.exceptions.NotFoundException("Indirizzo non trovato!"));
	}

	public Address create(AddressPayload p) {
		Address address = new Address(p.getVia(), p.getCivico(), p.getLocalità(), p.getCap(), p.getTipo(), p.getUser(),
				p.getComune());
		return address;
	}

	public Address findByIdAndUpdate(UUID id, AddressPayload p) throws NotFoundException {
		Address found = this.findById(id);
		found.setId(found.getId());
		found.setVia(p.getVia());
		found.setCivico(p.getCivico());
		found.setLocalità(p.getLocalità());
		found.setCap(p.getCap());
		found.setTipo(p.getTipo());
		found.setComune(p.getComune());
		return addressesRepo.save(found);
	}

	public void deleteAddress(UUID id) {
		addressesRepo.deleteById(id);
	}
}
