package app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Council;

@Repository
public interface CouncilRepository extends JpaRepository<Council, UUID> {

}
