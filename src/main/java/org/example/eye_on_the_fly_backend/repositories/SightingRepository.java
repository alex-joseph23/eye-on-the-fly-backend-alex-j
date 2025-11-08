package org.example.eye_on_the_fly_backend.repositories;

import org.example.eye_on_the_fly_backend.models.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SightingRepository extends JpaRepository<Sighting, Long> {
}
