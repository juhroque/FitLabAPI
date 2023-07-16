package com.julia.fitlab.dto.ficha;

import com.julia.fitlab.dto.aluno.DadosListagemAluno;
import com.julia.fitlab.dto.exercicioFicha.DadosListagemExercicioFicha;
import com.julia.fitlab.models.Ficha;

import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoFicha(
        DadosListagemAluno aluno,
        List<DadosListagemExercicioFicha> exerciciosDaFicha
) {
    public DadosDetalhamentoFicha(Ficha ficha){
        this(new DadosListagemAluno(ficha.getAluno()),
                ficha.getExercicios().stream()
                        .map(DadosListagemExercicioFicha::new)
                        .collect(Collectors.toList()));
    }
}
