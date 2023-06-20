package app.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Bill;
import app.entities.User;
import app.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	// -------------------------- GET SU USERS -----------------------------
	// Versione 1 (GET: http://localhost:3001/users) OK
	@GetMapping("")
	public List<User> getUsers() {
		return userService.find();
	}

	// -------------------------- POST SU USERS --------------------------------
	// Versione 2 e payload (POST: http://localhost:3001/users) OK
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody @Validated User body) {
		return userService.create(body);
	}

	// ----------------------- GET SU SINGOLO USER -----------------------------
	// Versione 1 (GET: http://localhost:3001/users/{idUser}) OK
	@GetMapping("/{idUser}")
	public User getUser(@PathVariable UUID idUser) throws Exception {
		return userService.findById(idUser);
	}

	// ------------ GET PER OTTENERE I DISPOSITIVI DEL SINGOLO USER -------------
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
