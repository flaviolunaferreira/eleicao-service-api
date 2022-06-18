package dataa.eleger.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/eleicao")
public class EleicaoControle {

    @GetMapping
    public ResponseEntity<String> testeControle() {
        return ResponseEntity.ok().body("tudo certo");
    }

}
