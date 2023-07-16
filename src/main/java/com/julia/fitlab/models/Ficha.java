package com.julia.fitlab.models;


import com.julia.fitlab.dto.ficha.DadosCadastroFicha;
import com.julia.fitlab.models.exercicio.ExercicioDaFicha;
import com.julia.fitlab.repositories.ExercicioDaFichaRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity(name="Ficha")
@Table(name="fichas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ficha {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ficha_exercicio",
            joinColumns = {
                    @JoinColumn(name = "ficha_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "exercicio_daficha_id", referencedColumnName = "id"),
            })
    private List<ExercicioDaFicha> exercicios = new ArrayList<>();

    private boolean ativo;


    public Ficha(Aluno aluno, List<ExercicioDaFicha> exerciciosDaFicha) {
        this.aluno = aluno;
        this.ativo = true;
        this.exercicios = exerciciosDaFicha;
    }

    public void excluir() {
        this.ativo = false;
    }
}
