package tn.tekup.meditrack.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dossier_medical")
@Getter @Setter @NoArgsConstructor
public class DossierMedical {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;             // patient fictif
    private String ownerUsername;   // proprietaire du dossier
    private String diagnostic;
    private String traitement;
    private String numeroSecu;      // FICTIF
}