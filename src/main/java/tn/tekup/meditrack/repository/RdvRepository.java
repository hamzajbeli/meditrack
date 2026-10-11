package tn.tekup.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.tekup.meditrack.model.RendezVous;
import java.util.List;

public interface RdvRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByPatientUsername(String patientUsername);
}