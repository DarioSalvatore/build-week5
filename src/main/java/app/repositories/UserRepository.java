package app.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	// La versione 1 non aveva questo metodo perché non c'era un controllo nel
	// metodo create del service
	Optional<User> findByEmail(String email);

	Page<User> findByFatturatoAnnualeBetween(double minFatturato, double maxFatturato, Pageable pageable);

	Page<User> findBydataInserimento(LocalDate dataInserimento, Pageable pageable);

	Page<User> findBydataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);

	@Query("SELECT u FROM User u WHERE lower(u.nomeContatto) LIKE lower(concat('%', :nomeContatto, '%')) "
			+ "OR lower(u.nomeContatto) LIKE lower(concat('%', initcap(:nomeContatto), '%'))")
	Page<User> findBynomeContattoIgnoreCase(String nomeContatto, Pageable pageable);

}
