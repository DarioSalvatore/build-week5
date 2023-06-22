package app.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import app.entities.Bill;
import app.entities.StatusBill;
import app.exceptions.NotFoundException;
import app.repositories.BillRepository;
import app.repositories.UserRepository;

@Service
public class BillService {
	@Autowired
	private BillRepository billRepo;

	@Autowired
	private UserRepository userRepo;

	// 1. create Bill
	public Bill create(Bill u) {
		return billRepo.save(u);
	}

	// 2. search all Bills
	public Page<Bill> findAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 20;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return billRepo.findAll(pageable);
	}

	// 3 search Bills by id
	public Bill findById(UUID id) throws NotFoundException {
		return billRepo.findById(id).orElseThrow(() -> new NotFoundException("Bill not found!"));
	}

	// 4. find by id and update
	public Bill findByIdAndUpdate(UUID id, Bill body) throws NotFoundException {
		Bill found = this.findById(id);

		found.setId(id);
		found.setAmount(body.getAmount());
		found.setDate(body.getDate());
		found.setNumber(body.getNumber());
		found.setStatusBill(body.getStatusBill());
		found.setYear(body.getYear());
		found.setUser(body.getUser());

		return billRepo.save(found);
	}

	// 5. find by id and delete
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Bill found = this.findById(id);
		billRepo.delete(found);
	}

	public long count() {
		return billRepo.count();
	}

	public List<Bill> getBillsByUserId(UUID userId) {
		return billRepo.findBillsByUserId(userId);
	}

	public List<Bill> getBillsByStatusBill(StatusBill status) {
		return billRepo.findByStatusBill(status);
	}

	public List<Bill> getBillsByDate(LocalDate date) {
		return billRepo.findByDate(date);
	}

	public List<Bill> getBillsByYear(int year) {
		return billRepo.findByYear(year);
	}

	public List<Bill> getBillsByAmount(double min, double max) {
		return billRepo.findByAmountBetween(min, max);
	}
}
