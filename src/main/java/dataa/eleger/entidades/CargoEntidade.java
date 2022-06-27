package dataa.eleger.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCargo;

    @Column(nullable = false)
    private String nomeCargo;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_candidato")
    private CandidatoEntidade candidato;

    private String criadoPor = System.getProperty("user.name");
    private LocalDateTime criadoData = LocalDateTime.now();

    public CargoEntidade(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
}
