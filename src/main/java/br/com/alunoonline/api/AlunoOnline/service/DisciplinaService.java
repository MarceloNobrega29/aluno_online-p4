package br.com.alunoonline.api.AlunoOnline.service;

import br.com.alunoonline.api.AlunoOnline.model.Disciplina;
import br.com.alunoonline.api.AlunoOnline.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    public void criarDisciplina(Disciplina disciplina) {
        disciplinaRepository.save(disciplina);
    }

    public Optional<Disciplina> buscarDisciplinaPorId(Long id) {
        return disciplinaRepository.findById(id);
    }

    public void deletarDisciplinaPorId(Long id) {
        disciplinaRepository.deleteById(id);
    }

    public void atualizarDisciplinaPorId(Long id, Disciplina disciplina) {
        Disciplina disciplinaBancoDeDados = disciplinaRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina não encontrada"));

        disciplinaBancoDeDados.setNome(disciplina.getNome());
        disciplinaBancoDeDados.setCargaHoraria(disciplina.getCargaHoraria());

        disciplinaRepository.save(disciplinaBancoDeDados);
    }

    public List<Disciplina> listarTodasDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public List<Disciplina> listarDisciplinasDoProf(Long id) {
        return disciplinaRepository.findByProfessor_Id(id);
    }
}