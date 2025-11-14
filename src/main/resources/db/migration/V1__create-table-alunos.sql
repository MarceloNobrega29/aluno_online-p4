CREATE TABLE aluno (
      id BIGINT NOT NULL AUTO_INCREMENT,
      nome VARCHAR(250) NOT NULL,
      cpf VARCHAR(14),
      email VARCHAR(250),
      PRIMARY KEY (id),
      UNIQUE KEY uk_aluno_cpf (cpf),
      UNIQUE KEY uk_aluno_email (email)
)