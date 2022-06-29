package dataa.eleger.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VotoEntidade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoto;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "idUsuario")
    private UsuarioEntidade usuario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idEleicao")
    private EleicaoEntidade eleicaoEntidade;

    @JsonIgnore
    @OneToMany(mappedBy = "votoEntidade")
    private List<ItensDoVoto> itensDoVoto = new ArrayList<>();

    private String protocolo;


    public VotoEntidade(EleicaoEntidade eleicaoEntidade, UsuarioEntidade usuarioEntidade) {
        this.eleicaoEntidade = eleicaoEntidade;
        this.usuario = usuarioEntidade;

    }
}
