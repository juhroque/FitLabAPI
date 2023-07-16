package com.julia.fitlab.models.exercicio;

import com.julia.fitlab.dto.exercicioFicha.DadosCadastroExercicioFicha;
import com.julia.fitlab.models.Ficha;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="ExercicioDaFicha")
@Table(name="exercicios_da_ficha")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioDaFicha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "exercicio_id", referencedColumnName = "id")
    private Exercicio exercicio;

    private Integer duracao;

    private Integer series;

    private Integer repeticoes;

    public ExercicioDaFicha(DadosCadastroExercicioFicha dados){
        this.duracao = dados.duracao();
        this.series = dados.series();
        this.repeticoes = dados.repeticoes();
    }

    public ExercicioDaFicha(Exercicio exercicio, int duracao, int series, int repeticoes){
        this.exercicio = exercicio;
        this.duracao = duracao;
        this.series = series;
        this.repeticoes = repeticoes;
    }

}
