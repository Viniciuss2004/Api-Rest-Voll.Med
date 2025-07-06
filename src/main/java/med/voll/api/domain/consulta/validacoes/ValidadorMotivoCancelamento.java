package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMotivoCancelamento implements ValidadorCancelamentoDeConsulta {

    public void validar(DadosCancelamentoConsulta dadosJson) {
        var motivo = dadosJson.motivo();

        if(motivo == null) {
            throw new ValidacaoException("Motivo do cancelamento é obrigatório!");
        }

        if(!motivo.equalsIgnoreCase("paciente desistiu") && !motivo.equalsIgnoreCase("médico cancelou") && !motivo.equalsIgnoreCase("outros")) {
            throw new ValidacaoException("Motivo de cancelamento inválido!");
        }
    }
}
