package tn.tekup.meditrack.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tn.tekup.meditrack.model.AppUser;
import tn.tekup.meditrack.model.DossierMedical;
import tn.tekup.meditrack.repository.AppUserRepository;
import tn.tekup.meditrack.repository.DossierRepository;

@Component
public class DataSeeder implements CommandLineRunner {
    private final AppUserRepository users;
    private final DossierRepository dossiers;
    private final PasswordEncoder encoder;

    public DataSeeder(AppUserRepository users, DossierRepository dossiers, PasswordEncoder encoder) {
        this.users = users;
        this.dossiers = dossiers;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        if (users.count() > 0) return;   // ne reinsere pas a chaque demarrage

        users.save(user("admin", "Admin#Demo1", "ADMIN"));
        users.save(user("dr.martin", "Doc#Demo1", "MEDECIN"));
        users.save(user("patient1", "Pat1#Demo1", "PATIENT"));
        users.save(user("patient2", "Pat2#Demo1", "PATIENT"));

        dossiers.save(dossier("Patient Un", "patient1", "Hypertension", "Traitement fictif A", "1 85 05 99 999 001"));
        dossiers.save(dossier("Patient Deux", "patient2", "Diabete type 2", "Traitement fictif B", "2 90 11 99 999 002"));
    }

    private AppUser user(String username, String rawPassword, String role) {
        AppUser u = new AppUser();
        u.setUsername(username);
        u.setPassword(encoder.encode(rawPassword));   // jamais en clair
        u.setRole(role);
        return u;
    }

    private DossierMedical dossier(String nom, String owner, String diag, String trait, String secu) {
        DossierMedical d = new DossierMedical();
        d.setNom(nom);
        d.setOwnerUsername(owner);
        d.setDiagnostic(diag);
        d.setTraitement(trait);
        d.setNumeroSecu(secu);
        return d;
    }
}