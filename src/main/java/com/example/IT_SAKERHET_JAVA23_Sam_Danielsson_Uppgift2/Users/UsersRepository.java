package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
