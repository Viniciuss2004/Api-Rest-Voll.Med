package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.consulta.validacoes.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadoresAgendamento;
    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dadosJson) {
        if (dadosJson.idMedico() != null && !medicoRepository.existsById(dadosJson.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        if (!pacienteRepository.existsById(dadosJson.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        validadoresAgendamento.forEach(v -> v.validar(dadosJson));

        var medico = escolherMedico(dadosJson);

        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        var paciente = pacienteRepository.getReferenceById(dadosJson.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dadosJson.data());

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    public void cancelarAgendamento(DadosCancelamentoConsulta dadosJson) {
        validadoresCancelamento.forEach(v -> v.validar(dadosJson));

        var consulta = consultaRepository.findById(dadosJson.idConsulta());

        if (consulta.isEmpty()) {
            throw new ValidacaoException("Nenhum consulta encontrada!");
        }

        consultaRepository.deleteById(dadosJson.idConsulta());
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dadosJson) {
        if (dadosJson.idMedico() != null) {
            return medicoRepository.getReferenceById(dadosJson.idMedico());
        }

        if (dadosJson.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória caso médico não seja escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dadosJson.especialidade(), dadosJson.data());
    }


}
