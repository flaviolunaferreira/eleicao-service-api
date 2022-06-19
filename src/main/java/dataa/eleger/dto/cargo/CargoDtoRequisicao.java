package dataa.eleger.dto.cargo;


import dataa.eleger.entidades.CargoEntidade;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoDtoRequisicao {

    private String nomeCargo;

    public CargoEntidade novoCargo() {
        return new CargoEntidade(nomeCargo);
    }
}
