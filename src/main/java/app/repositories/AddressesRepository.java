package app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Address;

@Repository
public interface AddressesRepository extends JpaRepository<Address, UUID> {

}
