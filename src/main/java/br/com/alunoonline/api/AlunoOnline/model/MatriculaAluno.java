package br.com.alunoonline.api.AlunoOnline.model;


import br.com.alunoonline.api.AlunoOnline.enuns.MatriculaAlunoStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "matricula_aluno")
@Entity
public class MatriculaAluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_id", nullable = false, foreignKey = @ForeignKey(name = "fk_matricula_aluno"))
    private Aluno aluno;

    @ManyToOne(optional = false)
    @JoinColumn(name = "disciplina_id", nullable = false, foreignKey = @ForeignKey(name = "fk_matricula_disciplina"))
    private Disciplina disciplina;

    @Column(name = "nota1")
    private Double nota1;

    @Column(name = "nota2")
    private Double nota2;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    private MatriculaAlunoStatusEnum status;

}