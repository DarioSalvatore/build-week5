package app.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entities.Bill;
import app.entities.StatusBill;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID> {
	List<Bill> findByYear(int year);

	List<Bill> findByDate(LocalDate date);

	List<Bill> findByAmountBetween(@Param("min") double min, @Param("max") double max);

	List<Bill> findByStatusBill(StatusBill status);

	List<Bill> findByUser(UUID userId);

//	@Query(nativeQuery = true, value = "SELECT * FROM bills JOIN users ON user.id = bills.user_id WHERE users.id = :id")
//	List<Bill> findByUser(@Param("id") UUID id);

}
