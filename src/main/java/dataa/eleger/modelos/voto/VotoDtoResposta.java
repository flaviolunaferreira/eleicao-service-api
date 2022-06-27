package dataa.eleger.modelos.voto;


import dataa.eleger.entidades.ItensDoVoto;
import dataa.eleger.entidades.VotoEntidade;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoDtoResposta {

    private Long idVoto;
    private String usuario;
    private String eleicaoEntidade;
    private List<ItensDoVoto> itensDoVoto;
    private String protocolo;


    public VotoDtoResposta(VotoEntidade novoVoto) {
        this.setIdVoto(novoVoto.getIdVoto());
        this.setUsuario(novoVoto.getUsuario().getNome());
        this.setEleicaoEntidade(novoVoto.getEleicaoEntidade().getNome());
        this.setItensDoVoto(novoVoto.getItensDoVoto());
    }
}
