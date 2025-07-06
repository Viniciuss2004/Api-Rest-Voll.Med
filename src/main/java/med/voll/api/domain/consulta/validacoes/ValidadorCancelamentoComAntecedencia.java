package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorCancelamentoComAntecedencia implements ValidadorCancelamentoDeConsulta {

    @Autowired
    ConsultaRepository consultaRepository;

    public void validar(DadosCancelamentoConsulta dadosJson) {

        var consultaAgendada = consultaRepository.findById(dadosJson.idConsulta());

        if (consultaAgendada.isEmpty()) {
            throw new ValidacaoException("Nenhuma Consulta Encontrada!");
        }

        var dataConsulta = consultaAgendada.get().getData();
        var dataCancelamento = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(dataCancelamento, dataConsulta).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta deve ser cancelada com antecedência mínima de 24 horas!");
        }
    }
}
