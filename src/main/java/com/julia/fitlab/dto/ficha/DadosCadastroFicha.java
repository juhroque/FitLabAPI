package com.julia.fitlab.dto.ficha;

import com.julia.fitlab.dto.exercicio.DadosCadastroExercicio;
import com.julia.fitlab.dto.exercicio.DadosListagemExercicio;
import com.julia.fitlab.dto.exercicioFicha.DadosCadastroExercicioFicha;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroFicha(

        @NotNull
        Long alunoId,

        @NotNull
        List<DadosCadastroExercicioFicha> exercicios
) {
}
