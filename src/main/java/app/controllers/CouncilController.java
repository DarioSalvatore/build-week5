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

import app.entities.Council;
import app.repositories.CouncilRepository;
import app.services.CouncilService;

@RestController
@RequestMapping("/users/addresses/councils")
public class CouncilController {

	@Autowired
	private CouncilService councilService;

	@Autowired
	private CouncilRepository councilRepo;

	@GetMapping("")
	public Page<Council> getAllCouncil(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "name") String sortBy) {
		return councilService.findAll(page, size, sortBy);
	}

	@GetMapping("/{id}")
	public Council getById(@PathVariable UUID id) throws Exception {
		return councilService.findById(id);
	}

	@PostMapping("")
	public Council saveCouncil(@RequestBody Council body) {
		Council council = councilService.create(body);
		return councilRepo.save(council);
	}

	@PutMapping("/{id}")
	public void findByIdAndUpdate(@PathVariable UUID id, @RequestBody Council body) {
		try {
			councilService.findByIdAndUpdate(id, body);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DeleteMapping("/{id}")
	public void deleteCouncil(@PathVariable UUID id) {
		councilService.deleteCouncil(id);
	}

}
