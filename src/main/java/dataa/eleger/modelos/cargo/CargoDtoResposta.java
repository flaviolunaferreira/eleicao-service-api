package dataa.eleger.modelos.cargo;

import dataa.eleger.entidades.CargoEntidade;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CargoDtoResposta {

    private Long idCargo;
    private String nomeCargo;
    private String criadoPor = System.getProperty("user.name");
    private LocalDateTime criadoData = LocalDateTime.now();

   public CargoDtoResposta(CargoEntidade cargoEntidade) {
       this.setIdCargo(cargoEntidade.getIdCargo());
       this.setNomeCargo(cargoEntidade.getNomeCargo());
       this.setCriadoPor(cargoEntidade.getCriadoPor());
       this.setCriadoData(cargoEntidade.getCriadoData());
   }

}
