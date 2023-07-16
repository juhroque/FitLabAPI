package com.julia.fitlab.dto.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroInstrutor(

        @NotNull
        @Email
        String login,

        @NotNull
        String senha
) {
}
