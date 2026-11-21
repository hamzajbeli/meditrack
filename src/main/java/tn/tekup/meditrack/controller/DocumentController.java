package tn.tekup.meditrack.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
public class DocumentController {

    private final Path base;

    public DocumentController(@Value("${meditrack.documents.dir:/tmp}") String dossier) {
        this.base = Paths.get(dossier).toAbsolutePath().normalize();
    }

    @GetMapping("/document")
    public ResponseEntity<String> lire(@RequestParam String nom) throws IOException {
        // 1. liste blanche : lettres, chiffres, point, tiret, underscore (donc ni / ni \)
        if (!nom.matches("[A-Za-z0-9._-]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nom de fichier invalide");
        }
        // 2. le chemin final doit rester dans le dossier autorise
        Path fichier = base.resolve(nom).normalize();
        if (!fichier.startsWith(base)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chemin non autorise");
        }
        if (!Files.isRegularFile(fichier)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document introuvable");
        }
        return ResponseEntity.ok(Files.readString(fichier));
    }
}