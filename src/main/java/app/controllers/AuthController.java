package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.auth.JWTTools;
import app.auth.payloads.AuthenticationSuccessfullPayload;
import app.entities.User;
import app.exceptions.NotFoundException;
import app.exceptions.UnauthorizedException;
import app.payloads.AdminPayload;
import app.payloads.UserPayload;
import app.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService usersService;

	@Autowired
	private PasswordEncoder bcrypt;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Validated UserPayload body) {
		body.setPassword(bcrypt.encode(body.getPassword()));
		User createdUser = usersService.create(body);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PostMapping("/register/admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<User> registerAdmin(@RequestBody @Validated AdminPayload body) {
		body.setPassword(bcrypt.encode(body.getPassword()));
		User createdAdmin = usersService.createAdmin(body);
		return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserPayload body)
			throws NotFoundException {

		// 1. Verificare che l'email dell'utente sia presente nel db
		User user = usersService.findByEmail(body.getEmail());
		String plainPW = body.getPassword();
		String hashedPW = user.getPassword();

		// 2. In caso affermativo devo verificare che la pw corrisponda a quella trovata
		// nel db
		if (!bcrypt.matches(plainPW, hashedPW))
			throw new UnauthorizedException("Credenziali non valide");
		// 3. Se tutto ok --> genero
		String token = JWTTools.createToken(user);
		// 4. Altrimenti --> 401 ("Credenziali non valide")

		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

}