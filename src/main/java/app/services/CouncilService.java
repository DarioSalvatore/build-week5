package app.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import app.entities.Council;
import app.entities.District;
import app.repositories.CouncilRepository;
import app.utils.CSVUtils;
import jakarta.transaction.Transactional;

@Service
public class CouncilService {

	@Autowired
	private CouncilRepository councilRepo;

	@Autowired
	private DistrictService districtService;

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
		Council council = new Council(p.getCodiceStorico(), p.getProgressivoComune(), p.getDenominazione(),
				p.getProvincia(), p.getIndirizzi());
		return council;
	}

	public Council findByIdAndUpdate(UUID id, Council p) throws NotFoundException {
		Council found = this.findById(id);
		found.setId(id);
		found.setCodiceStorico(p.getCodiceStorico());
		found.setProgressivoComune(p.getProgressivoComune());
		found.setDenominazione(p.getDenominazione());
		found.setProvincia(p.getProvincia());
		return councilRepo.save(found);
	}

	public void deleteCouncil(UUID id) {
		councilRepo.deleteById(id);
	}

	@Transactional
	public void importCouncilsFromCSV(String filePath) {
		List<Council> councils = councilRepo.findAll();
//		if (councils.size() == 0) {
		try {
			List<String[]> records = CSVUtils.readCSV(filePath);
			System.out.println("Numero di record: " + records.size());
			for (String[] record : records) {
				if (Arrays.toString(record).contains("Denominazione")) {
				} else {
//					System.out.println("Sono il record " + Arrays.toString(record));

					String codiceStorico = record[0].trim();
					String progressivoComune = record[1].trim();
					String denominazione = record[2].trim();
					String provincia = record[3].trim();
//					System.out.println("Sono la provincia " + provincia);
					District district = districtService.findByProvincia(provincia);
					Council council = new Council(codiceStorico, progressivoComune, denominazione, district);
					System.out.println("Prima del save, ID " + council.getId() + " cod. storico "
							+ council.getCodiceStorico() + " Prog. Comune " + council.getProgressivoComune());
					councilRepo.save(council);
					System.out.println("Dopo il save, ID " + council.getId() + " cod. storico "
							+ council.getCodiceStorico() + " Prog. Comune " + council.getProgressivoComune());
				}
			}

		} catch (Exception e) {
			System.out.println(e + "Error");
			;
		}
//		}
	}

}
