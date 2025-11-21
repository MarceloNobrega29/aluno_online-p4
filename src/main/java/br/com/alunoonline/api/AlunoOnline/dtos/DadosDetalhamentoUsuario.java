package br.com.alunoonline.api.AlunoOnline.dtos;

import br.com.alunoonline.api.AlunoOnline.model.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login
) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin());
    }
}