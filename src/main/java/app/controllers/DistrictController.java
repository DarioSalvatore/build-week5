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

import app.entities.District;
import app.repositories.DistrictRepository;
import app.services.DistrictService;

@RestController
@RequestMapping("/users/addresses/councils/districts")
public class DistrictController {

	@Autowired
	private DistrictService districtService;

	@Autowired
	private DistrictRepository districtRepo;

	@GetMapping("")
	public Page<District> getAllDistrict(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "name") String sortBy) {
		return districtService.findAll(page, size, sortBy);
	}

	@GetMapping("/{id}")
	public District getById(@PathVariable UUID id) throws Exception {
		return districtService.findById(id);
	}

	@PostMapping("")
	public District saveCouncil(@RequestBody District body) {
		District district = districtService.create(body);
		return districtRepo.save(district);
	}

	@PutMapping("/{id}")
	public void findByIdAndUpdate(@PathVariable UUID id, @RequestBody District body) {
		try {
			districtService.findByIdAndUpdate(id, body);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DeleteMapping("/{id}")
	public void deleteDistrict(@PathVariable UUID id) {
		districtService.deleteDistrict(id);
	}
}
