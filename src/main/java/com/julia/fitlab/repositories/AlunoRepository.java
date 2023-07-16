package com.julia.fitlab.repositories;

import com.julia.fitlab.models.Aluno;
import com.julia.fitlab.models.exercicio.Exercicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository  extends JpaRepository<Aluno, Long> {
    Page<Aluno> findAllByAtivoTrue(Pageable paginacao);
}

