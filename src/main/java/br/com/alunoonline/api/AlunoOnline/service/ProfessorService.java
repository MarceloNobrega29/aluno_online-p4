package br.com.alunoonline.api.AlunoOnline.service;


import br.com.alunoonline.api.AlunoOnline.model.Professor;
import br.com.alunoonline.api.AlunoOnline.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public void criarProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    public Optional<Professor> buscarProfessorPorId(Long id) {
        return professorRepository.findById(id);
    }

    public void deletarProfessorPorId(Long id) {
        professorRepository.deleteById(id);
    }

    public void atualizarProfessorPorId(Long id, Professor professor) {
        Professor professorBancoDeDados = professorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado"));

        professorBancoDeDados.setNome(professor.getNome());
        professorBancoDeDados.setEmail(professor.getEmail());

        professorRepository.save(professorBancoDeDados);
    }

    public List<Professor> listarTodosProfessores() {
        return professorRepository.findAll();
    }
}