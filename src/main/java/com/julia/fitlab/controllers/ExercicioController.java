package com.julia.fitlab.controllers;


import com.julia.fitlab.dto.aluno.DadosDetalhamentoAluno;
import com.julia.fitlab.dto.exercicio.DadosAtualizacaoExercicio;
import com.julia.fitlab.dto.exercicio.DadosCadastroExercicio;
import com.julia.fitlab.dto.exercicio.DadosDetalhamentoExercicio;
import com.julia.fitlab.dto.exercicio.DadosListagemExercicio;
import com.julia.fitlab.models.exercicio.ParteDoCorpo;
import com.julia.fitlab.repositories.ExercicioRepository;
import com.julia.fitlab.models.exercicio.Exercicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    @Autowired
    private ExercicioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroExercicio dados, UriComponentsBuilder uriBuilder){
        var exercicio = new Exercicio(dados);
        repository.save(exercicio);

        var uri = uriBuilder.path("/exercicios/{id}").buildAndExpand(exercicio.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemExercicio(exercicio));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemExercicio>> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemExercicio::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var exercicio = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoExercicio(exercicio));
    }

    @GetMapping("/parteDoCorpo/{parteDoCorpo}")
    public ResponseEntity<Page<DadosListagemExercicio>> listarPorParteDoCorpo(@PathVariable String parteDoCorpo, @PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
        ParteDoCorpo parte = ParteDoCorpo.valueOf(parteDoCorpo);
        var page = repository.findAllByParteDoCorpoAndAtivoTrue(parte, paginacao).map(DadosListagemExercicio::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoExercicio dados){
        var exercicio = repository.getReferenceById(dados.id());
        exercicio.atualizarExercicio(dados);
        return ResponseEntity.ok(new DadosListagemExercicio(exercicio));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var exercicio = repository.getReferenceById(id);
        exercicio.excluir();
        return ResponseEntity.noContent().build();
    }


}
