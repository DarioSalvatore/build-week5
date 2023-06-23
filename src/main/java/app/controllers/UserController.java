package app.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import app.entities.Council;
import app.entities.District;
import app.entities.User;
import app.payloads.UserPayload;
import app.repositories.DistrictRepository;
import app.repositories.UserRepository;
import app.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DistrictRepository districtRepo;

	// FatturatoAnnuale dato un range : (GET:
	// http://localhost:3001/users/filter?minFatturato=150&maxFatturato=180
	@GetMapping("/filter")
	public Page<User> findByFatturatoAnnualeRange(@RequestParam("minFatturato") double minFatturato,
			@RequestParam("maxFatturato") double maxFatturato, 
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "fatturatoAnnuale") String sortBy) {
		return userService.findByFatturatoAnnualeRange(minFatturato, maxFatturato, page, size, sortBy);
	}

	// filtra per data range(GET: http://localhost:3001/users/date?startDateFirstRange=1995-05-15&startDateSecondRange=2000-05-15)
	@GetMapping("/date")
	public Page<User> findByDate(
			@RequestParam("startDateFirstRange") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimentoFirst,
			@RequestParam("startDateSecondRange") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimentoSecond,
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "dataInserimento") String sortBy) {
		return userService.findBydataInserimentoRange(dataInserimentoFirst, dataInserimentoSecond, page, size, sortBy);
	}

	// filtra per dataUltimoContatto per range 
	// http://localhost:3001/users/dateLastContact?dataUltimoContattoFirstRange=1980-12-11&dataUltimoContattoSecondRange=2004-10-27
	@GetMapping("/dateLastContact")
	public Page<User> findBydataUltimoContatto(
			@RequestParam("dataUltimoContattoFirstRange") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContattoFirst,
			@RequestParam("dataUltimoContattoSecondRange") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContattoSecond,
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "40") int size,
			@RequestParam(defaultValue = "dataUltimoContatto") String sortBy) {
		return userService.findBydataUltimoContattoRange(dataUltimoContattoFirst,dataUltimoContattoSecond, page, size, sortBy);
	}

	// filtra per nome sia camel case che lower case
	// http://localhost:3001/users/name?name=elsa
	@GetMapping("/name")
	public Page<User> findByName(@RequestParam("name") String name, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "nomeContatto") String sortBy) {
		return userService.findBynomeContattoIgnoreCase(name, page, size, sortBy);
	}

	// -------------------------- GET SU USERS -----------------------------
	// Versione 1 (GET: http://localhost:3001/users) OK
	@GetMapping("")
	public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size, 
			@RequestParam(defaultValue = "ragioneSociale") String sortBy) {
		return userService.find(page, size, sortBy);
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
	public Page<Bill> findBillsUser(@PathVariable UUID idUser, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "number") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return userService.findBillsUser(idUser, pageable);
	}

	// ----------------------- PUT SU SINGOLO USER -----------------------------
	// Versione 1 (PUT: http://localhost:3001/users/{idUser}) OK
//	@PutMapping("/{idUser}")
//	public User updateUser(@PathVariable UUID idUser, @RequestBody User body) throws Exception {
//		return userService.findByIdAndUpdate(idUser, body);
//	}

	// TEST
	@PutMapping("/{idUser}")
	public ResponseEntity<User> register(@PathVariable UUID idUser, @RequestBody @Validated UserPayload body) {
		User createdUser = userService.findByIdAndUpdate(idUser, body);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	// -------------------- DELETE SU SINGOLO USER -----------------------------
	// Versione 1 (DELETE: http://localhost:3001/users/{idUser}) OK
	@DeleteMapping("/{idUser}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID idUser) throws Exception {
		userService.findByIdAndDelete(idUser);
	}

	@GetMapping("/district")
	public Page<User> findByProvincia(@RequestParam("provincia") String provincia,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return userService.findByProvincia(provincia, pageable);
	}

	@GetMapping("/{ragioneSociale}/sede_legale")
	public ResponseEntity<Map<String, String>> getSedeLegaleByRagioneSociale(
			@PathVariable("ragioneSociale") String ragioneSociale) {
		Council comune = userService.getComuneByRagioneSociale(ragioneSociale);
		District provincia = userService.getProvinciaByRagioneSociale(ragioneSociale);

		if (comune != null && provincia != null) {
			Map<String, String> sedeLegale = new HashMap<>();
			sedeLegale.put("comune", comune.getDenominazione());
			sedeLegale.put("provincia", provincia.getProvincia());
			return ResponseEntity.ok(sedeLegale);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
