package br.com.alunoonline.api.AlunoOnline.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank
        String login,

        @NotBlank
        String senha
) {}