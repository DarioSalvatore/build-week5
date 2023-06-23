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
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Page<User> findByFatturatoAnnualeRange(@RequestParam("minFatturato") double minFatturato,
			@RequestParam("maxFatturato") double maxFatturato, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "fatturatoAnnuale") String sortBy) {
		return userService.findByFatturatoAnnualeRange(minFatturato, maxFatturato, page, size, sortBy);
	}

	// filtra per data (GET: http://localhost:3001/users/date?startDate=1962-11-30)
	@GetMapping("/date")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Page<User> findByDate(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "dataInserimento") String sortBy) {
		return userService.findBydataInserimento(dataInserimento, page, size, sortBy);
	}

	// filtra per dataUltimoContatto
	// http://localhost:3001/users/dateLastContact?dataUltimoContatto=2000-09-01
	@GetMapping("/dateLastContact")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Page<User> findBydataUltimoContatto(
			@RequestParam("dataUltimoContatto") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "dataUltimoContatto") String sortBy) {
		return userService.findBydataUltimoContatto(dataUltimoContatto, page, size, sortBy);
	}

	// filtra per nome sia camel case che lower case
	// http://localhost:3001/users/name?name=elsa
	@GetMapping("/name")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Page<User> findByName(@RequestParam("name") String name, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "nomeContatto") String sortBy) {
		return userService.findBynomeContattoIgnoreCase(name, page, size, sortBy);
	}

	// -------------------------- GET SU USERS -----------------------------
	// Versione 1 (GET: http://localhost:3001/users) OK
	@GetMapping("")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "ragioneSociale") String sortBy) {
		return userService.find(page, size, sortBy);
	}

	// -------------------------- POST SU USERS --------------------------------
	// Versione 2 e payload (POST: http://localhost:3001/users) OK
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ADMIN')")
	public User saveUser(@RequestBody @Validated UserPayload body) {
		return userService.create(body);
	}

	// ----------------------- GET SU SINGOLO USER -----------------------------
	// Versione 1 (GET: http://localhost:3001/users/{idUser}) OK
	@GetMapping("/{idUser}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public User getUser(@PathVariable UUID idUser) throws Exception {
		return userService.findById(idUser);
	}

	// ------------ GET PER OTTENERE LE FATTURE DEL SINGOLO USER -------------
	// Versione 1 (GET: http://localhost:3001/users/{idUser}/dispositivi) OK

	@GetMapping("/{idUser}/bills")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Page<Bill> findBillsUser(@PathVariable UUID idUser, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "number") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return userService.findBillsUser(idUser, pageable);
	}

	// ----------------------- PUT SU SINGOLO USER -----------------------------
	// Versione 1 (PUT: http://localhost:3001/users/{idUser}) OK
	@PutMapping("/{idUser}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<User> register(@PathVariable UUID idUser, @RequestBody @Validated UserPayload body) {
		User createdUser = userService.findByIdAndUpdate(idUser, body);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	// -------------------- DELETE SU SINGOLO USER -----------------------------
	// Versione 1 (DELETE: http://localhost:3001/users/{idUser}) OK
	@DeleteMapping("/{idUser}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteUser(@PathVariable UUID idUser) throws Exception {
		userService.findByIdAndDelete(idUser);
	}

	@GetMapping("/district")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Page<User> findByProvincia(@RequestParam("provincia") String provincia,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return userService.findByProvincia(provincia, pageable);
	}

	@GetMapping("/{ragioneSociale}/sede_legale")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
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
