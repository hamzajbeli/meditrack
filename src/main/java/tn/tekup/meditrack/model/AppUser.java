package tn.tekup.meditrack.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_user")
@Getter @Setter @NoArgsConstructor
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;   // hash BCrypt, jamais en clair

    @Column(nullable = false)
    private String role;       // PATIENT, MEDECIN, ADMIN
}