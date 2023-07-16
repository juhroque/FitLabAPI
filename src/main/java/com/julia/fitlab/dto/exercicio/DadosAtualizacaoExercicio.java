package com.julia.fitlab.dto.exercicio;

import com.julia.fitlab.models.exercicio.ParteDoCorpo;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoExercicio(

        @NotNull
        Long id,
        String nome,
        ParteDoCorpo parteDoCorpo,
        Boolean cardio
) {
}
