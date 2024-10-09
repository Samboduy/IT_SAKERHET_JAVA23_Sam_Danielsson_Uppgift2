package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Users;

import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Timecapsule.Timecapsule;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Timecapsule> timecapsules;
}
