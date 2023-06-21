package app.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Bill;
import app.exceptions.NotFoundException;
import app.services.BillService;

@RestController
@RequestMapping("/users")
public class BillController {

	@Autowired
	BillService billService;

	// get all bills and order by name
	@GetMapping("/bills")
	public Page<Bill> getAllBill(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "name") String sortBy) {
		return billService.findAll(page, size, sortBy);
	}

	@PostMapping("{userId}/bills")
	@ResponseStatus(HttpStatus.CREATED)
	public Bill saveBill(@PathVariable UUID userId, @RequestBody Bill body) {
		return billService.create(body);
	}

	@GetMapping("/{userId}/bills")
	public Bill getBill(@PathVariable UUID userId) throws NotFoundException {
		return billService.findById(userId);
	}

	@PutMapping("/{userId}/bills/{billsId}")
	public Bill updateBill(@PathVariable UUID userId, @RequestBody Bill body, @PathVariable UUID billId)
			throws Exception {
		return billService.findByIdAndUpdate(billId, body);
	}

	// working
	@DeleteMapping("/{userId}/bills/{billsId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId, @PathVariable UUID billId) {
		billService.findByIdAndDelete(billId);
	}
}
