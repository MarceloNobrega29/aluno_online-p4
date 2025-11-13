package br.com.alunoonline.api.AlunoOnline.controller;

import br.com.alunoonline.api.AlunoOnline.model.Aluno;
import br.com.alunoonline.api.AlunoOnline.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar aluno", description = "Cria um novo registro de aluno no sistema")
    public void criarAluno(@RequestBody Aluno aluno) {
        alunoService.criarAluno(aluno);
    }

    @GetMapping("/{idAluno}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar aluno", description = "Busca um aluno pelo por ID")
    public Optional<Aluno> buscarAlunoPorId(@PathVariable Long id) {
        return alunoService.buscarAlunoPorId(id);
    }

    @DeleteMapping(("/{idAluno}"))
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Excluir aluno", description = "Remove um aluno do sistema por ID")
    public void deletarAlunoPorId(@PathVariable Long id) {
        alunoService.deletarAlunoPorId(id);
    }

    @PutMapping("/{idAluno}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Atualizar aluno", description = "Atualiza os dados de um aluno existente por ID")
    public void atualizarAlunoPorId(@PathVariable("idAluno") Long id, @RequestBody Aluno aluno) {
        alunoService.atualizarAlunoPorId(id, aluno);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar alunos", description = "Retorna todos os alunos cadastrados")
    public List<Aluno> listarTodosAlunos() {
        return alunoService.listarTodosAlunos();
    }
}