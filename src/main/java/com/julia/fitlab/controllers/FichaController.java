package com.julia.fitlab.controllers;

import com.julia.fitlab.dto.exercicioFicha.DadosCadastroExercicioFicha;
import com.julia.fitlab.dto.ficha.DadosCadastroFicha;
import com.julia.fitlab.dto.ficha.DadosDetalhamentoFicha;
import com.julia.fitlab.dto.ficha.DadosListagemFicha;
import com.julia.fitlab.models.Ficha;
import com.julia.fitlab.models.exercicio.Exercicio;
import com.julia.fitlab.models.exercicio.ExercicioDaFicha;
import com.julia.fitlab.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fichas")
public class FichaController {

    @Autowired
    private FichaRepository fichaRepository;
    @Autowired
    private ExercicioRepository exercicioRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ExercicioDaFichaRepository exercicioDaFichaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoFicha> mostrarFicha(@PathVariable Long id){
        var ficha = fichaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoFicha(ficha));
    }


    @PostMapping
    @Transactional
    public ResponseEntity cadastrarFicha(@RequestBody @Valid DadosCadastroFicha dados, UriComponentsBuilder uriBuilder) throws Exception{
        var aluno = alunoRepository.getReferenceById(dados.alunoId());


        List<ExercicioDaFicha> exerciciosDaFicha = new ArrayList<>();
        for(DadosCadastroExercicioFicha dadosCadastroExercicioFicha : dados.exercicios()){
            Exercicio ex = exercicioRepository.getReferenceById(dadosCadastroExercicioFicha.exercicioId());
            ExercicioDaFicha exercicioDaFicha = new ExercicioDaFicha(ex, dadosCadastroExercicioFicha.duracao(), dadosCadastroExercicioFicha.series(), dadosCadastroExercicioFicha.repeticoes());
            exerciciosDaFicha.add(exercicioDaFicha);
        }
        exercicioDaFichaRepository.saveAll(exerciciosDaFicha);
        Ficha ficha = new Ficha(aluno, exerciciosDaFicha);
        fichaRepository.save(ficha);
        var uri = uriBuilder.path("/fichas/{id}").buildAndExpand(ficha.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoFicha(ficha));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirFicha(@PathVariable Long id){
        var ficha = fichaRepository.getReferenceById(id);
        var aluno = ficha.getAluno();
        ficha.excluir();
        return ResponseEntity.noContent().build();
    }


}
