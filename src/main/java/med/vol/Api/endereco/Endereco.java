package med.vol.Api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
    }

    public void atualizarInformacoes(DadosEndereco dadosJson) {
        if (dadosJson.logradouro() != null) {
            this.logradouro = dadosJson.logradouro();
        }

        if (dadosJson.bairro() != null) {
            this.bairro = dadosJson.bairro();
        }

        if (dadosJson.cep() != null) {
            this.cep = dadosJson.cep();
        }

        if (dadosJson.numero() != null) {
            this.numero = dadosJson.numero();
        }

        if (dadosJson.complemento() != null) {
            this.complemento = dadosJson.complemento();
        }

        if (dadosJson.cidade() != null) {
            this.cidade = dadosJson.cidade();
        }

        if (dadosJson.uf() != null) {
            this.uf = dadosJson.uf();
        }
    }
}
