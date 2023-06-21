package app.controllers;

import java.time.LocalDate;
import java.util.List;
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
import app.entities.StatusBill;
import app.exceptions.NotFoundException;
import app.services.BillService;

@RestController
@RequestMapping("/bills")
public class BillController {

	@Autowired
	BillService billService;

	// get all bills and order by name
	// OK
	@GetMapping("")
	public Page<Bill> getAllBill(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "number") String sortBy) {
		return billService.findAll(page, size, sortBy);
	}

	// OK
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Bill saveBill(@RequestBody Bill body) {
		return billService.create(body);
	}

	// Ok
	@GetMapping("/{billId}")
	public Bill getBill(@PathVariable UUID billId) throws NotFoundException {
		return billService.findById(billId);
	}

	// OK
	@PutMapping("/{billId}")
	public Bill updateBill(@PathVariable UUID billId, @RequestBody Bill body) throws Exception {
		return billService.findByIdAndUpdate(billId, body);
	}

	// OK
	@DeleteMapping("/{billId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID billId) throws NotFoundException {
		billService.findByIdAndDelete(billId);
	}

	@GetMapping("bills/user")
	public List<Bill> getBillsByUser(@RequestParam("user") UUID userId) {
		return billService.getBillsByUser(userId);
	}

	@GetMapping("bills/status")
	public List<Bill> getBillsByStatusBill(@RequestParam("status") StatusBill status) {
		return billService.getBillsByStatusBill(status);
	}

	@GetMapping("bills/date")
	public List<Bill> getBillsByDate(@RequestParam("date") String date) {
		return billService.getBillsByDate(LocalDate.parse(date));
	}

	@GetMapping("bills/year")
	public List<Bill> getBillsByYear(@RequestParam("year") int year) {
		return billService.getBillsByYear(year);
	}

	@GetMapping("bills/amount")
	public List<Bill> getBillsByAmount(@RequestParam("min") double min, @RequestParam("max") double max) {
		return billService.getBillsByAmount(min, max);
	}

}
