package tn.tekup.meditrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.tekup.meditrack.model.AppUser;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}