CREATE TABLE professor (
      id BIGINT NOT NULL AUTO_INCREMENT,
      nome VARCHAR(250) NOT NULL,
      email VARCHAR(250),
      cpf VARCHAR(14),
      PRIMARY KEY (id),
      UNIQUE KEY uk_professor_email (email),
      UNIQUE KEY uk_professor_cpf (cpf)
)