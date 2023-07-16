package com.julia.fitlab.dto.exercicioFicha;

import com.julia.fitlab.models.exercicio.Exercicio;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroExercicioFicha(
        @NotNull
        Long exercicioId,

        int duracao,
        int series,
        int repeticoes
) {
}
