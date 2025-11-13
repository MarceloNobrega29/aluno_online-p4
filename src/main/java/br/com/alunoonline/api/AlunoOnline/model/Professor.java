package br.com.alunoonline.api.AlunoOnline.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "professor")
@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 250)
    private String nome;

    @Column(name = "email", length = 250, unique = true)
    private String email;

    @Column(name = "cpf", length = 14, unique = true)
    private String cpf;
}