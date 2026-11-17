package tn.tekup.meditrack.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecurityConfigTest {

    @Value("${local.server.port}")
    private int port;

    private int statut(String chemin) throws Exception {
        HttpRequest requete = HttpRequest
                .newBuilder(URI.create("http://localhost:" + port + chemin)).GET().build();
        return HttpClient.newHttpClient()
                .send(requete, HttpResponse.BodyHandlers.discarding()).statusCode();
    }

    @Test
    void actuatorEnvEstProtege() throws Exception {
        assertEquals(401, statut("/actuator/env"));
    }

    @Test
    void apiMeExigeUneAuthentification() throws Exception {
        assertEquals(401, statut("/api/me/dossier"));
    }
}
