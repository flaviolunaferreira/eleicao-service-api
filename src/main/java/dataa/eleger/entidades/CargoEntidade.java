package dataa.eleger.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private String nomeCargo;
    private String criadoPor = System.getProperty("user.name");
    private LocalDateTime criadoData = LocalDateTime.now();

    public CargoEntidade(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
}
