package app.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	// La versione 1 non aveva questo metodo perché non c'era un controllo nel
	// metodo create del service
	Optional<User> findByEmail(String email);

	List<User> findByFatturatoAnnualeGreaterThanEqual(double fatturatoAnnuale);

	List<User> findBydataInserimento(LocalDate dataInserimento);
	
	List<User> findBydataUltimoContatto(LocalDate dataUltimoContatto);
	
	@Query("SELECT u FROM User u WHERE lower(u.nomeContatto) LIKE lower(concat('%', :nomeContatto, '%')) "
			+ "OR lower(u.nomeContatto) LIKE lower(concat('%', initcap(:nomeContatto), '%'))")
	List<User> findBynomeContattoIgnoreCase(String nomeContatto);
	
	
}
