package br.com.alunoonline.api.AlunoOnline.controller;

import br.com.alunoonline.api.AlunoOnline.model.Professor;
import br.com.alunoonline.api.AlunoOnline.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar professor", description = "Registra um novo professor no sistema")
    public void criarProfessor(@RequestBody Professor professor) {
        professorService.criarProfessor(professor);
    }

    @GetMapping("/{idProfessor}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar professor", description = "Obtém os dados de um professor específico por ID")
    public Optional<Professor> buscarProfessorPorId(@PathVariable Long id) {
        return professorService.buscarProfessorPorId(id);
    }

    @DeleteMapping(("/{idProfessor}"))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover professor", description = "Exclui permanentemente um professor do sistema por ID")
    public void deletarProfessorPorId(@PathVariable Long id) {
        professorService.deletarProfessorPorId(id);
    }

    @PutMapping("/{idProfessor}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar professor", description = "Altera os dados cadastrais de um professor por ID")
    public void atualizarProfessorPorId(@PathVariable ("idProfessor") Long id, @RequestBody Professor professor) {
        professorService.atualizarProfessorPorId(id, professor);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar professores", description = "Retorna todos os professores cadastrados")
    public List<Professor> listarTodosProfessores() {
        return professorService.listarTodosProfessores();
    }

}