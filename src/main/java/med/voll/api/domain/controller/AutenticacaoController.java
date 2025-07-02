package med.voll.api.domain.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.infra.security.DadosTokenJWT;
import med.voll.api.domain.infra.security.TokenService;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dadosLogin){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosLogin.login(), dadosLogin.senha());
        var authenticantion = manager.authenticate(authenticationToken);

        var tokenJWT= tokenService.gerarToken((Usuario) authenticantion.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
