package com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Timecapsule;

import com.example.IT_SAKERHET_JAVA23_Sam_Danielsson_Uppgift2.Users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "timecapsule")
@Table(name = "timecapsule")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Timecapsule {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "message")
    private String message;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid",nullable = false)
    private Users users;
}
