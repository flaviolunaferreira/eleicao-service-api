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


   public CargoDtoResposta(CargoEntidade cargoEntidade) {
       this.setIdCargo(cargoEntidade.getIdCargo());
       this.setNomeCargo(cargoEntidade.getNomeCargo());
   }

}
