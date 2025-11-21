CREATE TABLE matricula_aluno (
      id BIGINT NOT NULL AUTO_INCREMENT,
      aluno_id BIGINT NOT NULL,
      disciplina_id BIGINT NOT NULL,
      nota1 DOUBLE,
      nota2 DOUBLE,
      status VARCHAR(30),
      PRIMARY KEY (id),
      KEY idx_matricula_aluno (aluno_id),
      KEY idx_matricula_disciplina (disciplina_id),
      CONSTRAINT fk_matricula_aluno
        FOREIGN KEY (aluno_id) REFERENCES aluno (id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
      CONSTRAINT fk_matricula_disciplina
        FOREIGN KEY (disciplina_id) REFERENCES disciplina (id)
        ON UPDATE CASCADE ON DELETE RESTRICT
)