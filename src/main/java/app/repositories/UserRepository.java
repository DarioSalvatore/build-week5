package app.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	// La versione 1 non aveva questo metodo perché non c'era un controllo nel
	// metodo create del service
	Optional<User> findByEmail(String email);
}
