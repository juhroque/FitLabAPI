package com.julia.fitlab.controllers;

import com.julia.fitlab.dto.aluno.DadosAtualizacaoAluno;
import com.julia.fitlab.dto.aluno.DadosCadastroAluno;
import com.julia.fitlab.dto.aluno.DadosDetalhamentoAluno;
import com.julia.fitlab.dto.aluno.DadosListagemAluno;
import com.julia.fitlab.dto.ficha.DadosDetalhamentoFicha;
import com.julia.fitlab.dto.ficha.DadosListagemFicha;
import com.julia.fitlab.models.Aluno;
import com.julia.fitlab.models.Ficha;
import com.julia.fitlab.repositories.AlunoRepository;
import com.julia.fitlab.repositories.FichaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;
    @Autowired
    private FichaRepository fichaRepository;


    @GetMapping
    public ResponseEntity<Page<DadosListagemAluno>> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemAluno::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var aluno = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping("/{id}/fichas")
    public ResponseEntity<Page<DadosListagemFicha>> listarFichas(@PathVariable Long id, @PageableDefault(size=10, sort={"ficha_id"}) Pageable paginacao){
        var aluno = repository.getReferenceById(id);
        List<DadosListagemFicha> listaFichas = new ArrayList<>();
        for (Long idFicha : aluno.getIdDasFichas()) {
            Ficha ficha = fichaRepository.findById(idFicha).orElse(null);
            if (ficha != null && ficha.isAtivo()) {
                DadosListagemFicha dadosListagemFicha = new DadosListagemFicha(ficha);
                listaFichas.add(dadosListagemFicha);
            }
        }
        Page<DadosListagemFicha> page = new PageImpl<>(listaFichas, PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()), listaFichas.size());
        return ResponseEntity.ok(page);
    }

}
