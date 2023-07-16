package com.julia.fitlab.dto.aluno;

import com.julia.fitlab.models.Aluno;
import com.julia.fitlab.models.Ficha;

import java.util.List;

public record DadosListagemAluno(
        Long id,
        String nome,
        String cpf,
        List<Long> fichas
) {

    public DadosListagemAluno(Aluno aluno){
        this(aluno.getId(), aluno.getNome(), aluno.getCpf(), aluno.getIdDasFichas());
    }
}
