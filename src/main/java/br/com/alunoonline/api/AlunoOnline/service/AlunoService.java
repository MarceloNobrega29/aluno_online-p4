package br.com.alunoonline.api.AlunoOnline.service;

import br.com.alunoonline.api.AlunoOnline.model.Aluno;
import br.com.alunoonline.api.AlunoOnline.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public void criarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
    }

    public Optional<Aluno> buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public void deletarAlunoPorId(Long id) {
        alunoRepository.deleteById(id);
    }

    public void atualizarAlunoPorId(Long id, Aluno aluno) {
        Aluno alunoModelExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));

        alunoModelExistente.setNome(aluno.getNome());
        alunoModelExistente.setEmail(aluno.getEmail());

        alunoRepository.save(alunoModelExistente);
    }

    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }
}

