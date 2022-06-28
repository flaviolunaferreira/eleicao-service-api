package dataa.eleger.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItensDoVoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdItemDoVoto;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "idCandidato")
    private CandidatoEntidade candidatoEntidade;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idVoto")
    private VotoEntidade votoEntidade;

    public ItensDoVoto(CandidatoEntidade candidato, VotoEntidade votoEntidade) {
        this.candidatoEntidade = candidato;
        this.votoEntidade = votoEntidade;
    }
}
