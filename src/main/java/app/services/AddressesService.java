package app.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	public Page<Address> findAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return addressesRepo.findAll(pageable);
	}
}
