package tn.tekup.meditrack.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class RendezVous {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientUsername;
    private String medecinUsername;
    private LocalDateTime date;
    private String motif;
}