package app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, UUID> {

}
