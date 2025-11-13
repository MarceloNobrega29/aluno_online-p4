package br.com.alunoonline.api.AlunoOnline.repository;

import br.com.alunoonline.api.AlunoOnline.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    List<Disciplina> findByProfessor_Id(Long id);
}
