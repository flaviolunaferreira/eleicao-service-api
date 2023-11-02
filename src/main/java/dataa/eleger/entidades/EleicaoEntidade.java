package dataa.eleger.entidades;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EleicaoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDateTime inicio;

    @Column(nullable = false)
    private LocalDateTime fim;

    @OneToMany
    @JoinColumn(name = "Candidato_idCandidato")
    private List<CandidatoEntidade> candidato = new ArrayList<>();

    @OneToMany(mappedBy = "eleicaoEntidade")
    private List<VotoEntidade>  votosEntidade;

    private String criadoPor = System.getProperty("user.name");
    private LocalDateTime criadoData = LocalDateTime.now();
    private String modificadoPor = System.getProperty("user.name");
    private LocalDateTime modificadoData = LocalDateTime.now();


    public EleicaoEntidade(String nome, LocalDateTime inicio, LocalDateTime fim) {
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
    }
}
