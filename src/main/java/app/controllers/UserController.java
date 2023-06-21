package app.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import app.entities.User;
import app.payloads.UserPayload;
import app.repositories.UserRepository;
import app.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;

	// -------------------------- GET SU USERS -----------------------------
	// Versione 1 (GET: http://localhost:3001/users) OK
	@GetMapping("")
	public List<User> getUsers() {
		return userService.find();
	}
	
	//FatturatoAnnuale maggiore di n : (GET: http://localhost:3001/users/filter?fatturato=180)
	@GetMapping("/filter")
	public List<User> findByFatturatoAnnuale(@RequestParam("fatturato") double fatturatoAnnuale){
		return userService.getUserByFatturatoAnnuale(fatturatoAnnuale);
	}
	
	
	// filtra per data (GET: http://localhost:3001/users/date?startDate=1962-11-30)
	@GetMapping("/date")
	public List<User> findByDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento){
				return userRepo.findBydataInserimento(dataInserimento);
	}
	
	//filtra per dataUltimoContatto http://localhost:3001/users/dateLastContact?dataUltimoContatto=2000-09-01
	@GetMapping("/dateLastContact")
	public List<User> findBydataUltimoContatto(@RequestParam("dataUltimoContatto") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto){
		return  userRepo.findBydataUltimoContatto(dataUltimoContatto);	
	}
	
	//filtra per nome sia camel case che lower case http://localhost:3001/users/name?name=elsa
	@GetMapping("/name")
	public List<User> findByName(@RequestParam("name") String name){
		return userRepo.findBynomeContattoIgnoreCase(name);
	}
	
	
	
	
	
	

	// -------------------------- POST SU USERS --------------------------------
	// Versione 2 e payload (POST: http://localhost:3001/users) OK
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody @Validated UserPayload body) {
		return userService.create(body);
	}

	// ----------------------- GET SU SINGOLO USER -----------------------------
	// Versione 1 (GET: http://localhost:3001/users/{idUser}) OK
	@GetMapping("/{idUser}")
	public User getUser(@PathVariable UUID idUser) throws Exception {
		return userService.findById(idUser);
	}

	// ------------ GET PER OTTENERE LE FATTURE DEL SINGOLO USER -------------
	// Versione 1 (GET: http://localhost:3001/users/{idUser}/dispositivi) OK
	@GetMapping("/{idUser}/bills")
	public List<Bill> findBillsUser(@PathVariable UUID idUser) {
		return userService.findBillsUser(idUser);
	}

	// ----------------------- PUT SU SINGOLO USER -----------------------------
	// Versione 1 (PUT: http://localhost:3001/users/{idUser}) OK
	@PutMapping("/{idUser}")
	public User updateUser(@PathVariable UUID idUser, @RequestBody User body) throws Exception {
		return userService.findByIdAndUpdate(idUser, body);
	}

	// -------------------- DELETE SU SINGOLO USER -----------------------------
	// Versione 1 (DELETE: http://localhost:3001/users/{idUser}) OK
	@DeleteMapping("/{idUser}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID idUser) throws Exception {
		userService.findByIdAndDelete(idUser);
	}

}
