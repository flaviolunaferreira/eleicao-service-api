package dataa.eleger.entidades;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidato;
    @Column(nullable = false)
    private String nomeCandidato;

    @OneToOne
    @JoinColumn(name = "idCargo")
    private CargoEntidade cargoEntidade;

    @Column(nullable = false)
    private String foto;

    private String criadoPor = System.getProperty("user.name");
    private LocalDateTime criadoData = LocalDateTime.now();
    private String modificadoPor = System.getProperty("user.name");
    private LocalDateTime modificadoData = LocalDateTime.now();

    public CandidatoEntidade(String nomeCandidato, CargoEntidade cargo, String foto) {
        this.nomeCandidato = nomeCandidato;
        this.cargoEntidade = cargo;
        this.foto = foto;
    }
}
