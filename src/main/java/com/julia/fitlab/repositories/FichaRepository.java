package com.julia.fitlab.repositories;

import com.julia.fitlab.models.Ficha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichaRepository extends JpaRepository<Ficha, Long> {
    Page<Ficha> findAllByAtivoTrue(Pageable paginacao);
}
