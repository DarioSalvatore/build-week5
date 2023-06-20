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

import app.entities.District;
import app.repositories.DistrictRepository;
import app.utils.CSVUtils;
import jakarta.transaction.Transactional;

@Service
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
		District district = new District(p.getProvincia(), p.getSigla(), p.getRegione());
		System.out.println("Provincia Creata");
		return districtRepo.save(district);

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

	@Transactional
	public void importDistrictsFromCSV(String filePath) {
		List<District> districts = districtRepo.findAll();
		if (districts.size() == 0) {
			try {
				List<String[]> records = CSVUtils.readCSV(filePath);
				for (String[] record : records) {
					if (Arrays.toString(record).contains("Sigla")) {
					} else {
						System.out.println(Arrays.toString(record));
						String sigla = record[0].trim();
						String provincia = record[1].trim();
						String regione = record[2].trim();

						System.out.println("Sono il record " + Arrays.toString(record));
						System.out.println(sigla + provincia + regione);
						District district = new District(sigla, provincia, regione);
						districtRepo.save(district);
					}
				}
			} catch (Exception e) {
				System.out.println(e + "Error");
				;
			}
		}
	}

}
