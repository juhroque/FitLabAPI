package com.julia.fitlab.dto.exercicio;

import com.julia.fitlab.models.exercicio.ParteDoCorpo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroExercicio(

        @NotBlank
        String nome,

        ParteDoCorpo parteDoCorpo,

        @NotBlank
        Boolean cardio
) {
}
