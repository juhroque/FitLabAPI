package com.julia.fitlab.repositories;

import com.julia.fitlab.models.Ficha;
import com.julia.fitlab.models.exercicio.ExercicioDaFicha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioDaFichaRepository extends JpaRepository<ExercicioDaFicha, Long> {
}
