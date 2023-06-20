package app.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import app.entities.Council;
import app.repositories.CouncilRepository;

@Service
public class CouncilService {

	@Autowired
	private CouncilRepository councilRepo;

	public Page<Council> findAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return councilRepo.findAll(pageable);
	}

	public Council findById(UUID id) throws NotFoundException {
		return councilRepo.findById(id)
				.orElseThrow(() -> new app.exceptions.NotFoundException("Indirizzo non trovato!"));
	}

	public Council create(Council p) {
		Council council = new Council(p.getNome(), p.getProvincia(), p.getIndirizzi(), p.getCodiceStorico(),
				p.getProgressivoComune());
		return council;
	}

	public Council findByIdAndUpdate(UUID id, Council p) throws NotFoundException {
		Council found = this.findById(id);
		found.setId(id);
		found.setNome(p.getNome());
		found.setProvincia(p.getProvincia());
		found.setCodiceStorico(p.getCodiceStorico());
		found.setProgressivoComune(p.getProgressivoComune());
		return councilRepo.save(found);
	}

	public void deleteCouncil(UUID id) {
		councilRepo.deleteById(id);
	}

}
