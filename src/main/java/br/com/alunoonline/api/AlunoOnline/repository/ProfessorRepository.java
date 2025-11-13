package br.com.alunoonline.api.AlunoOnline.repository;

import br.com.alunoonline.api.AlunoOnline.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}