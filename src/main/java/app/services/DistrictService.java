package app.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import app.entities.District;
import app.repositories.DistrictRepository;

public class DistrictService {

	@Autowired
	private DistrictRepository districtRepo;

	public Page<District> findAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return districtRepo.findAll(pageable);
	}

	public District findById(UUID id) throws NotFoundException {
		return districtRepo.findById(id)
				.orElseThrow(() -> new app.exceptions.NotFoundException("Indirizzo non trovato!"));
	}

	public District create(District p) {
		District district = new District(p.getProvincia(), p.getSigla(), p.getRegione(), p.getComuni());
		return district;
	}

	public District findByIdAndUpdate(UUID id, District p) throws NotFoundException {
		District found = this.findById(id);
		found.setId(id);
		found.setProvincia(p.getProvincia());
		found.setSigla(p.getSigla());
		found.setRegione(p.getRegione());
		found.setComuni(p.getComuni());
		return districtRepo.save(found);
	}

	public void deleteDistrict(UUID id) {
		districtRepo.deleteById(id);
	}

}
