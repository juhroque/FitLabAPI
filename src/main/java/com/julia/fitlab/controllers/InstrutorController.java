package com.julia.fitlab.controllers;

import com.julia.fitlab.dto.aluno.DadosAtualizacaoAluno;
import com.julia.fitlab.dto.aluno.DadosCadastroAluno;
import com.julia.fitlab.dto.aluno.DadosDetalhamentoAluno;
import com.julia.fitlab.dto.aluno.DadosListagemAluno;
import com.julia.fitlab.dto.ficha.DadosListagemFicha;
import com.julia.fitlab.dto.usuarios.DadosCadastroInstrutor;
import com.julia.fitlab.dto.usuarios.DadosLogin;
import com.julia.fitlab.dto.usuarios.DadosTokenJWT;
import com.julia.fitlab.models.Aluno;
import com.julia.fitlab.models.Ficha;
import com.julia.fitlab.models.Instrutor;
import com.julia.fitlab.repositories.AlunoRepository;
import com.julia.fitlab.repositories.FichaRepository;
import com.julia.fitlab.repositories.InstrutorRepository;
import com.julia.fitlab.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private InstrutorRepository repository;


    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity cadastrarInstrutor(@RequestBody @Valid DadosCadastroInstrutor dados){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senhaCodificada = passwordEncoder.encode(dados.senha());
        var instrutor = new Instrutor(dados);
        instrutor.setSenha(senhaCodificada);
        repository.save(instrutor);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/login")
    public ResponseEntity fazerLogin(@RequestBody @Valid DadosLogin dados){
        var authToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authToken);
        var tokenJWT = tokenService.gerarToken((Instrutor) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    //Alunos:

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping("/{id}/alunos")
    @Transactional
    public ResponseEntity cadastrarAluno(@RequestBody @Valid DadosCadastroAluno dados, @PathVariable Long id){
        var aluno = new Aluno(dados, repository.getReferenceById(id));
        alunoRepository.save(aluno);
        return ResponseEntity.ok().build();
    }



}
