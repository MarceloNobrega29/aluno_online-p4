package br.com.alunoonline.api.AlunoOnline.dtos;

import lombok.Data;
import java.util.List;

@Data
public class HistoricoAlunoResponseDTO {

    private String nomeAluno;
    private String email;
    private String cpf;
    private List<DisciplinasAlunoResponseDTO> disiciplinas;
}
