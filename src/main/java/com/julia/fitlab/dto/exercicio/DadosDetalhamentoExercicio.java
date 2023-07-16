package com.julia.fitlab.dto.exercicio;

import com.julia.fitlab.models.exercicio.Exercicio;
import com.julia.fitlab.models.exercicio.ParteDoCorpo;

public record DadosDetalhamentoExercicio(String nome, ParteDoCorpo parteDoCorpo, boolean cardio) {
    public DadosDetalhamentoExercicio(Exercicio exercicio){
        this(exercicio.getNome(), exercicio.getParteDoCorpo(), exercicio.isCardio());
    }
}
