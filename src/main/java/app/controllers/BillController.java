package app.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
import app.services.BillService;

@RestController
@RequestMapping("/bills")
public class BillController {
	
	@Autowired
	BillService billService;
		
		//get all bills and order by name 
		@GetMapping("")
		public Page<Bill> getAllBill(@RequestParam(defaultValue = "0") int page, 
				@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "name") String sortBy) {
			return billService.findAll(page, size, sortBy);
		}
		
		//working
		@PostMapping("")
		@ResponseStatus(HttpStatus.CREATED)
		public Bill saveBill(@RequestBody Bill body){
			return billService.create(body);
		}
		
		
		@GetMapping("/{billId}")
		public Bill getBill(@PathVariable UUID billId) throws NotFoundException {
			return billService.findById(billId);
		}
		
		//working
		@PutMapping("/{billId}")
		public Bill updateBill(@PathVariable UUID billId, @RequestBody Bill body) throws Exception {
			return billService.findByIdAndUpdate(billId, body);
		}
		
		//working
		@DeleteMapping("/{billId}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void deleteUser(@PathVariable UUID billId){
			billService.findByIdAndDelete(billId);
		}
}
