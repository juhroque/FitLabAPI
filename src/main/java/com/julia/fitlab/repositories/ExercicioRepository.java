package com.julia.fitlab.repositories;

import com.julia.fitlab.models.exercicio.ParteDoCorpo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.julia.fitlab.models.exercicio.Exercicio;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    Page<Exercicio> findAllByAtivoTrue(Pageable paginacao);
    Page<Exercicio> findAllByParteDoCorpoAndAtivoTrue(ParteDoCorpo parteDoCorpo, Pageable paginas);

}
