package dataa.eleger.modelos.candidatos;

import dataa.eleger.entidades.CandidatoEntidade;
import dataa.eleger.entidades.CargoEntidade;
import dataa.eleger.repositorios.CargoRepositorio;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoDtoRequest {

    @NotBlank(message = "Nome do Candidato não pode ser nulo.")
    @NotNull(message = "Nome do Candidato não pode estar em branco.")
    private String nomeCandidato;

    @NotBlank(message = "Deve ser informado o id do Cargo.")
    @NotNull(message = "Deve ser informado o id do Cargo.")
    private Long idCargo;

    @NotBlank(message = "Deve ser informado o Link da Foto.")
    @NotNull(message = "Deve ser informado o Link da Foto.")
    private String foto;


    public CandidatoEntidade newCandidato(CargoRepositorio cargoRepositorio) {
        CargoEntidade cargo = cargoRepositorio.findById(idCargo).get();
        return new CandidatoEntidade(nomeCandidato, cargo, foto);
    }
}
