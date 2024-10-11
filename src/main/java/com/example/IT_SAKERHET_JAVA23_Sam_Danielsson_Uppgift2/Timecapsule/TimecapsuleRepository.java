package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Timecapsule;

import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimecapsuleRepository extends JpaRepository<Timecapsule, Long> {
    List<Timecapsule> getTimecapsulesByUsers(Users user);
    Timecapsule getTimecapsulesByIdAndAndUsers(Long id, Users user);
}
