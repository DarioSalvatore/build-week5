package app.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entities.Council;
import app.entities.District;
import app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	// La versione 1 non aveva questo metodo perché non c'era un controllo nel
	// metodo create del service
	Optional<User> findByEmail(String email);

	Page<User> findByFatturatoAnnualeBetween(double minFatturato, double maxFatturato, Pageable pageable);

	Page<User> findBydataInserimentoBetween(LocalDate dataInserimentoFirst, LocalDate dataInserimentoSecond, Pageable pageable);

	Page<User> findBydataUltimoContattoBetween(LocalDate dataUltimoContattoFirst,LocalDate dataUltimoContattoSecond, Pageable pageable);

	@Query("SELECT u FROM User u WHERE lower(u.nomeContatto) LIKE lower(concat('%', :nomeContatto, '%')) "
			+ "OR lower(u.nomeContatto) LIKE lower(concat('%', initcap(:nomeContatto), '%'))")
	Page<User> findBynomeContattoIgnoreCase(String nomeContatto, Pageable pageable);

	@Query("SELECT u FROM User u JOIN u.indirizzi a WHERE a.comune.provincia = :provincia")
	Page<User> findByProvincia(@Param("provincia") District provincia, Pageable pageable);

	@Query("SELECT a.comune FROM User u JOIN u.indirizzi a WHERE u.ragioneSociale = :ragioneSociale AND a.tipo = 'SEDE_LEGALE'")
	Council findComuneByRagioneSociale(@Param("ragioneSociale") String ragioneSociale);

	@Query("SELECT a.comune.provincia FROM User u JOIN u.indirizzi a WHERE u.ragioneSociale = :ragioneSociale AND a.tipo = 'SEDE_LEGALE'")
	District findProvinciaByRagioneSociale(@Param("ragioneSociale") String ragioneSociale);
}