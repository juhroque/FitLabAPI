package com.julia.fitlab.dto.exercicioFicha;

import com.julia.fitlab.dto.exercicio.DadosListagemExercicio;
import com.julia.fitlab.models.exercicio.ExercicioDaFicha;

public record DadosListagemExercicioFicha(

        DadosListagemExercicio exercicio,

        int duracao,
        int series,
        int repeticoes
) {
    public DadosListagemExercicioFicha(ExercicioDaFicha exercicioDaFicha){
        this(new DadosListagemExercicio(exercicioDaFicha.getExercicio()), exercicioDaFicha.getDuracao(), exercicioDaFicha.getSeries(), exercicioDaFicha.getRepeticoes());
    }
}
