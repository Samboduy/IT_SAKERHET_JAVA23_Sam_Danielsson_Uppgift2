package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Timecapsule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimecapsuleRepository extends JpaRepository<Timecapsule, Long> {
}
