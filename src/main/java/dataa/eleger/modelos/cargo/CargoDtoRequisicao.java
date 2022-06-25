package dataa.eleger.modelos.cargo;


import dataa.eleger.entidades.CargoEntidade;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoDtoRequisicao {

    @NotBlank(message = "Nome do cargo não pode estar em branco.")
    @NotNull(message = "Nome do cargo não podee ser vazio.")
    private String nomeCargo;

    public CargoEntidade novoCargo() {
        return new CargoEntidade(nomeCargo);
    }
}
