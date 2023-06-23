package app.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import app.entities.Address;
import app.entities.Bill;
import app.entities.Council;
import app.entities.District;
import app.entities.User;
import app.exceptions.BadRequestException;
import app.exceptions.NotFoundException;
import app.payloads.UserPayload;
import app.repositories.BillRepository;
import app.repositories.DistrictRepository;
import app.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private DistrictRepository districtRepo;

	// -------------------------- GET SU USERS -----------------------------
	// Versione 1 (GET: http://localhost:3001/users) OK
//	public List<User> find() {
//		return userRepo.findAll();
//	}

	public Page<User> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 20;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return userRepo.findAll(pageable);
	}

	// -------------------------- POST SU USERS --------------------------------
	// Versione 3 con controllo e payload (POST: http://localhost:3001/users) OK
	public User create(UserPayload u) {
		userRepo.findByEmail(u.getEmail()).ifPresent(user -> {
			throw new BadRequestException(
					"ATTENZIONE!!! L'email con la quale stai cercando di registarti è già in uso da un altro user");
		});
		List<Bill> fatture = new ArrayList<>();
		List<Address> indirizzi = new ArrayList<>();
		User newUser = new User(u.getRagioneSociale(), u.getPartitaIva(), u.getEmail(), u.getFatturatoAnnuale(),
				u.getPec(), u.getTelefono(), u.getMailContatto(), u.getPassword(), u.getNomeContatto(),
				u.getCognomeContatto(), u.getTelefonoContatto(), u.getTipo(), indirizzi, fatture);
		return userRepo.save(newUser);
	}

	// ----------------------- GET SU SINGOLO USER -----------------------------
	// Versione 1 (GET: http://localhost:3001/users/{idUser}) OK
	public User findById(UUID id) throws NotFoundException {
		return userRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! L'User cercato non è stato trovato!"));
	}

	// ----------------------- PUT SU SINGOLO USER -----------------------------
	// Versione 1 (PUT: http://localhost:3001/users/{idUser}) OK
//	public User findByIdAndUpdate(UUID id, User u) throws NotFoundException {
//		User found = this.findById(id);
//
//		found.setId(id);
//		found.setRagioneSociale(u.getRagioneSociale());
//		found.setPartitaIva(u.getPartitaIva());
//		found.setEmail(u.getEmail());
//		found.setDataInserimento(u.getDataInserimento());
//		found.setDataUltimoContatto(u.getDataUltimoContatto());
//		found.setFatturatoAnnuale(u.getFatturatoAnnuale());
//		found.setPec(u.getPec());
//		found.setTelefono(u.getTelefono());
//		found.setMailContatto(u.getMailContatto());
//		found.setPassword(u.getPassword());
//		found.setNomeContatto(u.getNomeContatto());
//		found.setCognomeContatto(u.getCognomeContatto());
//		found.setTelefono(u.getTelefono());
//		found.setTipo(u.getTipo());
//
//		return userRepo.save(found);
//	}

	public User findByIdAndUpdate(UUID id, UserPayload u) throws NotFoundException {
		User found = this.findById(id);

		found.setId(id);
		found.setRagioneSociale(u.getRagioneSociale());
		found.setPartitaIva(u.getPartitaIva());
		found.setEmail(u.getEmail());
//		found.setDataInserimento(u.getDataInserimento());
//		found.setDataUltimoContatto(u.getDataUltimoContatto());
		found.setFatturatoAnnuale(u.getFatturatoAnnuale());
		found.setPec(u.getPec());
		found.setTelefono(u.getTelefono());
		found.setMailContatto(u.getMailContatto());
		found.setPassword(u.getPassword());
		found.setNomeContatto(u.getNomeContatto());
		found.setCognomeContatto(u.getCognomeContatto());
		found.setTelefono(u.getTelefono());
		found.setTipo(u.getTipo());

		return userRepo.save(found);
	}

	// -------------------- DELETE SU SINGOLO USER -----------------------------
	// Versione 1 (DELETE: http://localhost:3001/users/{idUser}) OK
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		User found = this.findById(id);
		userRepo.delete(found);
	}

	// ------------------------ ALTRI METODI ----------------------
	public User findByEmail(String email) throws NotFoundException {
		return userRepo.findByEmail(email).orElseThrow(
				() -> new NotFoundException("ATTENZIONE!!! L'email che stai cercando non è stata trovata"));
	}

	public Page<Bill> findBillsUser(UUID id, Pageable pageable) {
		User found = this.findById(id);
		return billRepository.findByUser(found, pageable);
	}

	public Page<User> findByFatturatoAnnualeRange(double minFatturato, double maxFatturato, int page, int size,
			String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return userRepo.findByFatturatoAnnualeBetween(minFatturato, maxFatturato, pageable);
	}

	public Page<User> findBydataInserimentoRange(LocalDate dataInserimentoFirst,LocalDate dataInserimentoSecond, int page, int size, String sortBy) {
		if (size < 0)
			size = 20;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return userRepo.findBydataInserimentoBetween(dataInserimentoFirst, dataInserimentoSecond, pageable);
	}

	public Page<User> findBydataUltimoContattoRange(LocalDate dataUltimoContattoFirst,LocalDate dataUltimoContattoSecond, int page, int size, String sortBy) {
		if (size < 0)
			size = 20;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return userRepo.findBydataUltimoContattoBetween(dataUltimoContattoFirst, dataUltimoContattoSecond, pageable);
	}

	public Page<User> findBynomeContattoIgnoreCase(String name, int page, int size, String sortBy) {
		if (size < 0)
			size = 20;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return userRepo.findBynomeContattoIgnoreCase(name, pageable);
	}

	public Page<User> findByProvincia(String provincia, Pageable pageable) {
		Optional<District> districtOptional = districtRepo.findByProvincia(provincia);
		if (districtOptional.isPresent()) {
			return userRepo.findByProvincia(districtOptional.get(), pageable);
		}
		throw new IllegalArgumentException("Provincia non valida: " + provincia);
	}

	public District getProvinciaByRagioneSociale(String ragioneSociale) {
		return userRepo.findProvinciaByRagioneSociale(ragioneSociale);
	}

	public Council getComuneByRagioneSociale(String ragioneSociale) {
		return userRepo.findComuneByRagioneSociale(ragioneSociale);
	}
}
