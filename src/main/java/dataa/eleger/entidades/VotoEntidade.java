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

    @ManyToOne
    @JoinColumn(name = "idEleicao")
    private EleicaoEntidade eleicaoEntidade;

    @OneToMany(mappedBy = "votoEntidade")
    private List<ItensDoVoto> itensDoVoto = new ArrayList<>();

    private String protocolo;


    public VotoEntidade(EleicaoEntidade eleicaoEntidade, UsuarioEntidade usuarioEntidade) {
        this.eleicaoEntidade = eleicaoEntidade;
        this.usuario = usuarioEntidade;

    }
}
