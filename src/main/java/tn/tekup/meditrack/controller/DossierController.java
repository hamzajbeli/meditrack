package tn.tekup.meditrack.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.tekup.meditrack.model.DossierMedical;
import tn.tekup.meditrack.repository.DossierRepository;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DossierController {
    private final DossierRepository repo;

    public DossierController(DossierRepository repo) {
        this.repo = repo;
    }

    // Un patient voit uniquement SON dossier
    @GetMapping("/me/dossier")
    public DossierMedical monDossier(Authentication auth) {
        return repo.findByOwnerUsername(auth.getName()).orElseThrow();
    }

    // Medecin / Admin : liste de tous les dossiers
    @GetMapping("/dossiers")
    public List<DossierMedical> tous() {
        return repo.findAll();
    }
}