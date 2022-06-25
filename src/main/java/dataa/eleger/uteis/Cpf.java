package dataa.eleger.uteis;


import org.springframework.stereotype.Service;

@Service
public class Cpf {

    public String formataCpf(String cpf) {
        // pega o que foi digitado.
        String CPF = cpf;
        // tira os espaços em branco
        CPF = CPF.trim();

        //tira pontos e outros caracteres
        CPF = CPF.replace(",", "").replace(".", "").replace("-", "").replace("/", "");

        // formata CPF
        String finalCPF = CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-" + CPF.substring(9, 11);

        return finalCPF;
    }


}
