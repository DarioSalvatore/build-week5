package app.repositories;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entities.Bill;
import app.entities.StatusBill;
import app.entities.User;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID> {
	Page<Bill> findByYear(int year, Pageable pageable);

	Page<Bill> findByDate(LocalDate date, Pageable pageable);

	Page<Bill> findByAmountBetween(@Param("min") double min, @Param("max") double max, Pageable pageable);

	Page<Bill> findByStatusBill(StatusBill status, Pageable pageable);

//	@Query(nativeQuery = true, value = "SELECT * FROM bills JOIN users ON user.id = bills.user_id WHERE users.id = :id")
	// riferito a Bill
//	List<Bill> findByUser(@Param("id") UUID id);

	// riferito a User
	Page<Bill> findByUser(User user, Pageable pageable);

}
