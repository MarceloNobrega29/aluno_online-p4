package br.com.alunoonline.api.AlunoOnline.service;

import br.com.alunoonline.api.AlunoOnline.dtos.AtualizarNotasRequestDTO;
import br.com.alunoonline.api.AlunoOnline.dtos.DisciplinasAlunoResponseDTO;
import br.com.alunoonline.api.AlunoOnline.dtos.HistoricoAlunoResponseDTO;
import br.com.alunoonline.api.AlunoOnline.enuns.MatriculaAlunoStatusEnum;
import br.com.alunoonline.api.AlunoOnline.model.MatriculaAluno;
import br.com.alunoonline.api.AlunoOnline.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MatriculaAlunoService {

    private static final Double MEDIA_PARA_APROVACAO = 7.0;
    private static final Integer QTD_NOTAS = 2;

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    public void criarMatricula(MatriculaAluno matriculaAluno) {
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void trancarMatricula(Long matriculaAlunoId) {

        MatriculaAluno matriculaAlunoModel = matriculaAlunoRepository.findById(matriculaAlunoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula aluno não foi encontrada."));

        if (matriculaAlunoModel.getStatus().equals(MatriculaAlunoStatusEnum.MATRICULADO)) {
            matriculaAlunoModel.setStatus(MatriculaAlunoStatusEnum.TRANCADO);
            matriculaAlunoRepository.save(matriculaAlunoModel);
        } else {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possivel trancar com o estatus MATRICULADO.");
        }
    }

    public void atualizarNotas(Long matriculaAlunoId, AtualizarNotasRequestDTO atualizarNotasRequestDTO) {
        MatriculaAluno matriculaAluno = buscarMatriculaOuLancarExecao(matriculaAlunoId);

        if (atualizarNotasRequestDTO.getNota1() != null) {
            matriculaAluno.setNota1(atualizarNotasRequestDTO.getNota1());
        }
        if (atualizarNotasRequestDTO.getNota2() != null) {
            matriculaAluno.setNota2(atualizarNotasRequestDTO.getNota2());
        }

        calcularMediaEmodificarStatus(matriculaAluno);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public HistoricoAlunoResponseDTO emitirHistorico(Long idAluno) {
        List<MatriculaAluno> matriculaAlunos = matriculaAlunoRepository.findByAluno_Id(idAluno);

        if (matriculaAlunos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno não possui matriculas");
        }
        HistoricoAlunoResponseDTO historicoAluno = new HistoricoAlunoResponseDTO();

        historicoAluno.setNomeAluno(matriculaAlunos.get(0).getAluno().getNome());
        historicoAluno.setEmail(matriculaAlunos.get(0).getAluno().getEmail());
        historicoAluno.setCpf(matriculaAlunos.get(0).getAluno().getCpf());

        List<DisciplinasAlunoResponseDTO> disciplinas = matriculaAlunos.stream()
                .map(this::mapearParaDisciplinasAlunosResponseDTO).toList();

        historicoAluno.setDisiciplinas(disciplinas);
        return historicoAluno;

    }

    private MatriculaAluno buscarMatriculaOuLancarExecao(Long matriculaAlunoId) {
        return matriculaAlunoRepository.findById(matriculaAlunoId).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula do Aluno não encontrada"));
    }

    private void calcularMediaEmodificarStatus(MatriculaAluno matriculaAluno) {
        Double nota1 = matriculaAluno.getNota1();
        Double nota2 = matriculaAluno.getNota2();

        if (nota1 != null && nota2 != null) {
            Double media = (nota1 + nota2) / QTD_NOTAS;            // ? = if e : = else
            matriculaAluno.setStatus(media >= MEDIA_PARA_APROVACAO ? MatriculaAlunoStatusEnum.APROVADO : MatriculaAlunoStatusEnum.REPROVADO);
        }
    }

    private DisciplinasAlunoResponseDTO mapearParaDisciplinasAlunosResponseDTO(MatriculaAluno matriculaAluno) {
        DisciplinasAlunoResponseDTO response = new DisciplinasAlunoResponseDTO();

        response.setNomeDisciplina(matriculaAluno.getDisciplina().getNome());
        response.setNomeProfessor(matriculaAluno.getDisciplina().getProfessor().getNome());
        response.setNota1(matriculaAluno.getNota1());
        response.setNota2(matriculaAluno.getNota2());
        response.setMedia(calcularMedia(matriculaAluno.getNota1(), matriculaAluno.getNota2()));
        response.setStatus(matriculaAluno.getStatus());

        return response;
    }

    private Double calcularMedia(Double nota1, Double nota2) {
        return (nota1 != null && nota2 != null) ? (nota1 + nota2) / QTD_NOTAS : null;

    }
}