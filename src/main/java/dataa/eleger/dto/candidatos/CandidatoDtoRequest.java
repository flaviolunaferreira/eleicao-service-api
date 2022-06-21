package dataa.eleger.dto.candidatos;

import dataa.eleger.entidades.CandidatoEntidade;
import dataa.eleger.entidades.CargoEntidade;
import dataa.eleger.repositorios.CargoRepositorio;
import dataa.eleger.service.CargoService;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoDtoRequest {

    private String nomeCandidato;
    private Long idCargo;
    private String foto;


    public CandidatoEntidade newCandidato(CandidatoDtoRequest candidatoDtoRequest, CargoService cargoService) {
        CargoEntidade cargo = cargoService.buscarPorId(idCargo);
        return new CandidatoEntidade(nomeCandidato, cargo, foto);
    }
}
