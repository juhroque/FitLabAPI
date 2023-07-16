package com.julia.fitlab.repositories;

import com.julia.fitlab.models.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
    UserDetails findByLogin(String login);
}
