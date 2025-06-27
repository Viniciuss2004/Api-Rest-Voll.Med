package med.vol.Api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.Api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dadosJson) {
        repository.save(new Medico(dadosJson));
    }

    @GetMapping
    public Page<DadosListagemMedicos> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicos::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosJson) {
        var medico = repository.getReferenceById(dadosJson.id());
        medico.atualizarInformacoes(dadosJson);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
