package tn.tekup.meditrack.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import tn.tekup.meditrack.repository.DossierRepository;

class ConsultationControllerTest {

    @Test
    void rechercheUtiliseUneRequeteParametree() {
        JdbcTemplate jdbc = mock(JdbcTemplate.class);
        DossierRepository repo = mock(DossierRepository.class);
        List<Map<String, Object>> attendu = List.of(Map.of("id", 1L));
        String sql = "SELECT id, nom, diagnostic FROM dossier_medical WHERE nom LIKE ?";
        when(jdbc.queryForList(sql, "%dupont%")).thenReturn(attendu);

        ConsultationController controller = new ConsultationController(repo, jdbc);

        assertEquals(attendu, controller.recherche("dupont"));
    }
}
