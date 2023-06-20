package app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entities.Address;
import app.entities.Bill;
import app.entities.User;
import app.exceptions.BadRequestException;
import app.exceptions.NotFoundException;
import app.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	// -------------------------- GET SU USERS -----------------------------
	// Versione 1 (GET: http://localhost:3001/users) OK
	public List<User> find() {
		return userRepo.findAll();
	}

	// -------------------------- POST SU USERS --------------------------------
	// Versione 3 con controllo e payload (POST: http://localhost:3001/users) OK
	public User create(User u) {
		userRepo.findByEmail(u.getEmail()).ifPresent(user -> {
			throw new BadRequestException(
					"ATTENZIONE!!! L'email con la quale stai cercando di registarti è già in uso da un altro user");
		});
		List<Bill> fatture = new ArrayList<>();
		List<Address> indirizzi = new ArrayList<>();
		User newUser = new User(u.getRagioneSociale(), u.getPartitaIva(), u.getEmail(), u.getDataInserimento(),
				u.getDataUltimoContatto(), u.getFatturatoAnnuale(), u.getPec(), u.getTelefono(), u.getMailContatto(),
				u.getNomeContatto(), u.getCognomeContatto(), u.getTelefonoContatto(), u.getTipo(), indirizzi, fatture);
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
	public User findByIdAndUpdate(UUID id, User u) throws NotFoundException {
		User found = this.findById(id);

		found.setId(id);
		found.setRagioneSociale(u.getRagioneSociale());
		found.setPartitaIva(u.getPartitaIva());
		found.setEmail(u.getEmail());
		found.setDataInserimento(u.getDataInserimento());
		found.setDataUltimoContatto(u.getDataUltimoContatto());
		found.setFatturatoAnnuale(u.getFatturatoAnnuale());
		found.setPec(u.getPec());
		found.setTelefono(u.getTelefono());
		found.setMailContatto(u.getMailContatto());
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

	public List<Bill> findBillsUser(UUID id) {
		User found = this.findById(id);

		return found.getFatture();
	}

}
