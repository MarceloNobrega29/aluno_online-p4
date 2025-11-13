package br.com.alunoonline.api.AlunoOnline.controller;

import br.com.alunoonline.api.AlunoOnline.dtos.AtualizarNotasRequestDTO;
import br.com.alunoonline.api.AlunoOnline.dtos.HistoricoAlunoResponseDTO;
import br.com.alunoonline.api.AlunoOnline.model.MatriculaAluno;
import br.com.alunoonline.api.AlunoOnline.service.MatriculaAlunoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
public class MatriculaAlunoController {

    @Autowired
    MatriculaAlunoService matriculaAlunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria uma nova matrícula", description = "Cadastra uma nova matrícula de aluno no sistema")
    public void criarMatricula(@RequestBody MatriculaAluno matriculaAluno) {
        matriculaAlunoService.criarMatricula(matriculaAluno);
    }

    @PatchMapping("/trancar/{idMatricula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Trancar matrícula", description = "Altera o status da matrícula para trancado por ID")
    public void trancarMatricula(@PathVariable Long id) {
        matriculaAlunoService.trancarMatricula(id);
    }

    @PatchMapping("/atualizar-notas/{idMatricula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Atualizar Notas", description = "Atualiza as notas de uma matricula por ID")
    public void atualizarNotas(@PathVariable Long id, @RequestBody AtualizarNotasRequestDTO atualizarNotasRequestDTO) {
        matriculaAlunoService.atualizarNotas(id, atualizarNotasRequestDTO);
    }

    @GetMapping("/emitir-historico/{idAluno}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Emitir historico", description = "Emite o histórico acadêmico do aluno com base no ID informado.")
    public HistoricoAlunoResponseDTO emitirHistorico(@PathVariable Long id) {
        return matriculaAlunoService.emitirHistorico(id);
    }

}