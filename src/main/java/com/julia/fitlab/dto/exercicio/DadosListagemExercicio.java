package com.julia.fitlab.dto.exercicio;

import com.julia.fitlab.models.exercicio.ParteDoCorpo;
import com.julia.fitlab.models.exercicio.Exercicio;

public record DadosListagemExercicio(

        Long id,
        String nome,
        ParteDoCorpo parteDoCorpo,
        Boolean cardio
) {

    public DadosListagemExercicio(Exercicio exercicio){
        this(exercicio.getId(), exercicio.getNome(), exercicio.getParteDoCorpo(), exercicio.isCardio());
    }
}
