package app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Address;

public interface AddressesRepository extends JpaRepository<Address, UUID> {

}
