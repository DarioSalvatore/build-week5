package app.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

	@Query("SELECT b FROM Bill b WHERE b.user.id = :userId")
	List<Bill> findBillsByUserId(@Param("userId") UUID userId);

}
