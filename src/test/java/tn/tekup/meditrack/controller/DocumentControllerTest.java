package tn.tekup.meditrack.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DocumentControllerTest {

    @TempDir
    static Path dossier;

    @DynamicPropertySource
    static void proprietes(DynamicPropertyRegistry registry) {
        registry.add("meditrack.documents.dir", () -> dossier.toString());
    }

    @Value("${local.server.port}")
    int port;

    private HttpResponse<String> appeler(String chemin) throws Exception {
        String auth = Base64.getEncoder()
                .encodeToString("patient1:Pat1#Demo1".getBytes(StandardCharsets.UTF_8));
        HttpRequest requete = HttpRequest
                .newBuilder(URI.create("http://localhost:" + port + chemin))
                .header("Authorization", "Basic " + auth).GET().build();
        return HttpClient.newHttpClient().send(requete, HttpResponse.BodyHandlers.ofString());
    }

    @Test
    void traversalDeCheminRefuse() throws Exception {
        assertEquals(400, appeler("/api/me/document?nom=../../etc/passwd").statusCode());
    }

    @Test
    void nomAvecSeparateurRefuse() throws Exception {
        assertEquals(400, appeler("/api/me/document?nom=a/b.txt").statusCode());
    }

    @Test
    void documentAbsentRenvoie404() throws Exception {
        assertEquals(404, appeler("/api/me/document?nom=absent.txt").statusCode());
    }

    @Test
    void documentPresentEstLu() throws Exception {
        Files.writeString(dossier.resolve("test.txt"), "bonjour");
        HttpResponse<String> reponse = appeler("/api/me/document?nom=test.txt");
        assertEquals(200, reponse.statusCode());
        assertEquals("bonjour", reponse.body());
    }
}