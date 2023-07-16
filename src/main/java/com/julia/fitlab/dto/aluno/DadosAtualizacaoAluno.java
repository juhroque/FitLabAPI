package com.julia.fitlab.dto.aluno;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoAluno(
        @NotNull Long id,
        String nome,

        @Pattern(regexp = "\"(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)\"")
        String cpf) {

}
