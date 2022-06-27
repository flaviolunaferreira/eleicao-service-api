package dataa.eleger.entidades;

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

    @OneToOne
    @JoinColumn(name = "idCandidato")
    private CandidatoEntidade candidatoEntidade;

    public ItensDoVoto(CandidatoEntidade candidato) {
        this.candidatoEntidade = candidato;
    }
}
