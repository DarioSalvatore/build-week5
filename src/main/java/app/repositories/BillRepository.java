package app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID>{

}
