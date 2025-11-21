package br.com.alunoonline.api.AlunoOnline.controller;

import br.com.alunoonline.api.AlunoOnline.dtos.DadosCadastroUsuario;
import br.com.alunoonline.api.AlunoOnline.dtos.DadosDetalhamentoUsuario;
import br.com.alunoonline.api.AlunoOnline.model.Usuario;
import br.com.alunoonline.api.AlunoOnline.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cadastros")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {

        if (repository.findByLogin(dados.login()) != null) {
            return ResponseEntity.badRequest().body("Login já cadastrado.");
        }

        var senhaCriptografada = passwordEncoder.encode(dados.senha());

        var usuario = new Usuario(dados.login(), senhaCriptografada);

        repository.save(usuario);

        var uri = uriBuilder.path("/cadastros/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }
}