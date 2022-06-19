package dataa.eleger.entidades;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EleicaoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate inicio;

    @Column(nullable = false)
    private LocalDate fim;
    
    private String criadoPor = System.getProperty("user.name");
    private LocalDateTime criadoData = LocalDateTime.now();
    private String modificadoPor = System.getProperty("user.name");
    private LocalDateTime modificadoData = LocalDateTime.now();


    public EleicaoEntidade(String nome, LocalDate inicio, LocalDate fim) {
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;

    }
}
