package tn.tekup.meditrack.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ATTENTION : code VOLONTAIREMENT VULNERABLE (projet pedagogique).
 */
@RestController
@RequestMapping("/api/me")
public class DocumentController {

    private static final String DOSSIER_DOCUMENTS = "/tmp/";

    // F4 - Path traversal : le nom du fichier n'est pas valide
    @GetMapping("/document")
    public String lire(@RequestParam String nom) throws IOException {
        return Files.readString(Paths.get(DOSSIER_DOCUMENTS + nom));
    }
}
