package tn.tekup.meditrack.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.tekup.meditrack.model.DossierMedical;
import tn.tekup.meditrack.repository.DossierRepository;
import java.util.List;
import java.util.Map;

/**
 * ATTENTION : code VOLONTAIREMENT VULNERABLE (projet pedagogique).
 */
@RestController
@RequestMapping("/api/me")
public class ConsultationController {
    private final DossierRepository repo;
    private final JdbcTemplate jdbc;

    public ConsultationController(DossierRepository repo, JdbcTemplate jdbc) {
        this.repo = repo;
        this.jdbc = jdbc;
    }

    // F2 - IDOR : aucun controle que le dossier appartient a l'utilisateur connecte
    @GetMapping("/dossiers/{id}")
    public DossierMedical parId(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    // F1 - Injection SQL : l'entree est concatenee dans la requete
    @GetMapping("/recherche")
    public List<Map<String, Object>> recherche(@RequestParam String nom) {
        String sql = "SELECT id, nom, diagnostic FROM dossier_medical WHERE nom LIKE '%" + nom + "%'";
        return jdbc.queryForList(sql);
    }
}