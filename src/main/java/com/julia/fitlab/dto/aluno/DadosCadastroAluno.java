package com.julia.fitlab.dto.aluno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroAluno(
             @NotBlank
             String nome,

             @NotBlank
             @Pattern(regexp = "\"(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)\"")
             String cpf

) {

}

