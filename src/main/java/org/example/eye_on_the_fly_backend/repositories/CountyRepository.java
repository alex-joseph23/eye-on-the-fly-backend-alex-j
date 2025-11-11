package org.example.eye_on_the_fly_backend.repositories;
import org.example.eye_on_the_fly_backend.models.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CountyRepository extends JpaRepository<County, Long> {
    County findByNameIgnoreCase(String name);
}
