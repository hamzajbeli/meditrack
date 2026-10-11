package tn.tekup.meditrack.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.tekup.meditrack.model.AppUser;
import tn.tekup.meditrack.repository.AppUserRepository;

@Service
public class DbUserDetailsService implements UserDetailsService {
    private final AppUserRepository repo;

    public DbUserDetailsService(AppUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser u = repo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur inconnu"));
        return User.withUsername(u.getUsername())
            .password(u.getPassword())
            .roles(u.getRole())
            .build();
    }
}