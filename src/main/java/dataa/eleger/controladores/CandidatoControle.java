package dataa.eleger.controladores;

import dataa.eleger.dto.candidatos.CandidatoDtoRequest;
import dataa.eleger.dto.candidatos.CandidatoDtoResposta;
import dataa.eleger.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/candidato")
public class CandidatoControle {

    private final CandidatoService candidatoService;

    @Autowired
    public CandidatoControle(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    public ResponseEntity<CandidatoDtoResposta> salvarNovoCandidato(
            @RequestBody CandidatoDtoRequest candidatoDtoRequest) {
        return new ResponseEntity<>(candidatoService.novoCandidato(candidatoDtoRequest), HttpStatus.CREATED);
    }


}
