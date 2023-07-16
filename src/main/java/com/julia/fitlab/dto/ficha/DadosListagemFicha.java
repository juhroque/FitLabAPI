package com.julia.fitlab.dto.ficha;

import com.julia.fitlab.dto.aluno.DadosListagemAluno;
import com.julia.fitlab.dto.exercicioFicha.DadosListagemExercicioFicha;
import com.julia.fitlab.models.Ficha;

import java.util.List;
import java.util.stream.Collectors;

public record DadosListagemFicha(
        Long id,
        DadosListagemAluno aluno,
        List<DadosListagemExercicioFicha> exerciciosDaFicha
) {
    public DadosListagemFicha(Ficha ficha){
        this(ficha.getId(),
                new DadosListagemAluno(ficha.getAluno()),
                ficha.getExercicios().stream()
                        .map(DadosListagemExercicioFicha::new)
                        .collect(Collectors.toList())
        );
    }
}
