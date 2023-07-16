package com.julia.fitlab.dto.aluno;

import com.julia.fitlab.models.Aluno;

import java.util.List;

public record DadosDetalhamentoAluno(String nome, String cpf, List<Long> idDasFichasDoAluno, Long instrutorId) {

    public DadosDetalhamentoAluno(Aluno aluno){
        this(aluno.getNome(), aluno.getCpf(), aluno.getIdDasFichas(), aluno.getIdInstrutor());
    }

}
