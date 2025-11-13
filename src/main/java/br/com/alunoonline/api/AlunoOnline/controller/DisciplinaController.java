package br.com.alunoonline.api.AlunoOnline.controller;

import br.com.alunoonline.api.AlunoOnline.model.Disciplina;
import br.com.alunoonline.api.AlunoOnline.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    DisciplinaService disciplinaService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Criar disciplina", description = "Cadastra uma nova disciplina no sistema.")
    public void criarDisciplina(@RequestBody Disciplina disciplina) {
        disciplinaService.criarDisciplina(disciplina);
    }

    @GetMapping("/{idDisciplina}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar disciplina", description = "Recupera os dados de uma disciplina específica por ID")
    public Optional<Disciplina> buscarDisciplinaPorId(@PathVariable Long id) {
        return disciplinaService.buscarDisciplinaPorId(id);
    }

    @DeleteMapping(("/{idDisciplina}"))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir disciplina", description = "Remove permanentemente uma disciplina do sistema por ID")
    public void deletarDisciplinaPorId(@PathVariable Long id) {
        disciplinaService.deletarDisciplinaPorId(id);
    }

    @PutMapping("/{idDisciplina}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar disciplina", description = "Altera os dados de uma disciplina existente por ID")
    public void atualizarDisciplinaPorId(@PathVariable ("idDisciplina") Long id, @RequestBody Disciplina disciplina) {
        disciplinaService.atualizarDisciplinaPorId(id, disciplina);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar todas disciplinas", description = "Retorna uma lista com todas as disciplinas cadastradas.")
    public List<Disciplina> listarTodasDisciplinas() {
        return disciplinaService.listarTodasDisciplinas();
    }

    @GetMapping("/professor/{idProfessor}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar todas disciplinas do professor", description = "Retorna uma lista com todas as disciplinas cadastradas em um professor.")
    List<Disciplina> listarDisciplinasDoProf(@PathVariable Long id) {
        return disciplinaService.listarDisciplinasDoProf(id);
    }

}
