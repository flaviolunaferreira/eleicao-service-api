package dataa.eleger.modelos.voto;

import dataa.eleger.entidades.*;
import dataa.eleger.repositorios.EleicaoRepositorio;
import dataa.eleger.repositorios.UsuarioRepositorio;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoDtoRequisicao {

    private Long usuario;
    private Long eleicao;


    public VotoEntidade novoVoto(EleicaoRepositorio eleicaoRepositorio, UsuarioRepositorio usuarioRepositorio) {
        UsuarioEntidade usuarioEntidade = usuarioRepositorio.findById(usuario).get();
        EleicaoEntidade eleicaoEntidade = eleicaoRepositorio.findById(eleicao).get();
        return new VotoEntidade(eleicaoEntidade, usuarioEntidade);
    }
}
