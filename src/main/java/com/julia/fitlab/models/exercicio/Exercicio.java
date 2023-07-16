package com.julia.fitlab.models.exercicio;

import com.julia.fitlab.dto.exercicio.DadosAtualizacaoExercicio;
import com.julia.fitlab.dto.exercicio.DadosCadastroExercicio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Exercicio")
@Table(name="exercicios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private ParteDoCorpo parteDoCorpo;

    private boolean cardio;

    private boolean ativo;

    public Exercicio(DadosCadastroExercicio dados){
        this.ativo = true;
        this.nome = dados.nome();
        this.cardio = dados.cardio();
        this.parteDoCorpo = dados.parteDoCorpo();
    }

    public void atualizarExercicio(DadosAtualizacaoExercicio dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.parteDoCorpo() != null) {
            this.parteDoCorpo = dados.parteDoCorpo();
        }
        if(dados.cardio() != null){
            this.cardio = dados.cardio();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}