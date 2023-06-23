package app.controllers;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
import app.payloads.BillPayload;
import app.services.BillService;
import app.services.UserService;

@RestController
@RequestMapping("/bills")
public class BillController {

	@Autowired
	BillService billService;

	@Autowired
	UserService userService;

	// get all bills and order by name
	// OK
	@GetMapping("")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Page<Bill> getAllBill(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "number") String sortBy) {
		return billService.findAll(page, size, sortBy);
	}

	// OK
//	@PostMapping("")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Bill saveBill(@RequestBody Bill body) {
//		return billService.create(body);
//	}

	// TEST
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ADMIN')")
	public Bill saveBill(@RequestBody @Validated BillPayload body) {
		return billService.create2(body);
	}

	// Ok
	@GetMapping("/{billId}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Bill getBill(@PathVariable UUID billId) throws NotFoundException {
		return billService.findById(billId);
	}

	// OK
	@PutMapping("/{billId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Bill updateBill(@PathVariable UUID billId, @RequestBody Bill body) throws Exception {
		return billService.findByIdAndUpdate(billId, body);
	}

	// OK
	@DeleteMapping("/{billId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteUser(@PathVariable UUID billId) throws NotFoundException {
		billService.findByIdAndDelete(billId);
	}

	// Non mettere paginazione
//	@GetMapping("/user")
//	public List<Bill> getBillsByUser(@RequestParam("user") UUID userId) {
//		return billService.getBillsByUser(userId);
//	}

	// OK Aggiungere Paginazione
	@GetMapping("/status")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Page<Bill> getBillsByStatusBill(@RequestParam("status") StatusBill status,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "number") String sortBy) {
		return billService.getBillsByStatusBill(status, page, size, sortBy);
	}

	// OK Aggiungere Paginazione
	@GetMapping("/date")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Page<Bill> getBillsByDate(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam(defaultValue = "0") int page,

			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "number") String sortBy) {
		LocalDate parsedStartDate = LocalDate.parse(startDate);
		LocalDate parsedEndDate = LocalDate.parse(endDate);
		return billService.getBillsByDate(parsedStartDate, parsedEndDate, page, size, sortBy);
	}

	// OK Aggiungere Paginazione
	@GetMapping("/year")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Page<Bill> getBillsByYear(@RequestParam("startYear") int startYear, @RequestParam("endYear") int endYear,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "number") String sortBy) {
		return billService.getBillsByYear(startYear, endYear, page, size, sortBy);
	}

	// OK Aggiungere Paginazione
	@GetMapping("/rangeofamounts")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Page<Bill> getBillsByAmount(@RequestParam("min") double min, @RequestParam("max") double max,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "number") String sortBy) {
		return billService.getBillsByAmount(min, max, page, size, sortBy);
	}

}
