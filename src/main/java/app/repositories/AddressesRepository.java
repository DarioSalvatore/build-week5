package app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Address;
import app.entities.AddressType;
import app.entities.User;

@Repository
public interface AddressesRepository extends JpaRepository<Address, UUID> {
	boolean existsByUserAndTipo(User user, AddressType tipo);
}
