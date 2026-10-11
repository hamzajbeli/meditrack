package tn.tekup.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.tekup.meditrack.model.DossierMedical;
import java.util.Optional;

public interface DossierRepository extends JpaRepository<DossierMedical, Long> {
    Optional<DossierMedical> findByOwnerUsername(String ownerUsername);
}