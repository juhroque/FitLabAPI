package com.julia.fitlab.models;

import com.julia.fitlab.dto.aluno.DadosAtualizacaoAluno;
import com.julia.fitlab.dto.aluno.DadosCadastroAluno;
import com.julia.fitlab.repositories.InstrutorRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity(name="Aluno")
@Table(name="alunos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;


    private String cpf;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ficha> fichas;

    @ManyToOne
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

    private boolean ativo;

    public Aluno(DadosCadastroAluno dados, Instrutor instrutor) {
        this.ativo = true;
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.instrutor = instrutor;
    }

    public Aluno(DadosCadastroAluno dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.cpf = dados.cpf();
    }

    public List<Long> getIdDasFichas(){
        List<Long> listaDoId = new ArrayList<>();
        for(Ficha ficha:this.fichas){
            if(ficha.isAtivo()){
                listaDoId.add(ficha.getId());
            }
        }
        return listaDoId;
    }

    public Long getIdInstrutor(){
        return this.instrutor.getId();
    }

    public void atualizarInformacoes(DadosAtualizacaoAluno dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.cpf() != null){
            this.cpf = dados.cpf();
        }
    }

    public void excluir(){
        this.ativo = false;
    }
}
