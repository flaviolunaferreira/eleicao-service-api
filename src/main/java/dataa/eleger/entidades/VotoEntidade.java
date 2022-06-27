package dataa.eleger.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoEntidade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoto;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    private UsuarioEntidade usuario;

    @OneToOne
    @JoinColumn(name = "idEleicao")
    private EleicaoEntidade eleicaoEntidade;

    @OneToMany
    @JoinColumn(name = "idItensDoVoto")
    private List<ItensDoVoto> itensDoVoto = new ArrayList<>();

    private String protocolo;


    public VotoEntidade(EleicaoEntidade eleicaoEntidade, UsuarioEntidade usuarioEntidade) {
        this.eleicaoEntidade = eleicaoEntidade;
        this.usuario = usuarioEntidade;

    }
}
