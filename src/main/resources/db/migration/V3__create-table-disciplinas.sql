CREATE TABLE disciplina (
      id BIGINT NOT NULL AUTO_INCREMENT,
      nome VARCHAR(200) NOT NULL,
      carga_horaria INT,
      professor_id BIGINT,
      PRIMARY KEY (id),
      KEY idx_disciplina_professor (professor_id),
      CONSTRAINT fk_disciplina_professor
        FOREIGN KEY (professor_id) REFERENCES professor (id)
        ON UPDATE CASCADE ON DELETE SET NULL
)