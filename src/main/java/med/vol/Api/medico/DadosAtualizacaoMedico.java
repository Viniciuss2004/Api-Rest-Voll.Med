package med.vol.Api.medico;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.Api.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(

        @NotNull
        Long id,

        String nome,

        @Pattern(regexp = "\\d{10,11}")
        String telefone,

        DadosEndereco endereco
) {
}
