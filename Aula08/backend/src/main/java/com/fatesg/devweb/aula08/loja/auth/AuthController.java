package com.fatesg.devweb.aula08.loja.auth;
import com.fatesg.devweb.aula08.loja.security.JwtService;
import com.fatesg.devweb.aula08.loja.usuario.Usuario;
import com.fatesg.devweb.aula08.loja.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public record CredenciaisRequest(
            @NotBlank @Email String email,
            @NotBlank @Size(min = 6, max = 72) String senha) { }

    public record TokenResponse(String token) { }

    private final UsuarioRepository usuarios;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwt;

    public AuthController(UsuarioRepository usuarios, PasswordEncoder encoder,
                          AuthenticationManager authManager, JwtService jwt) {
        this.usuarios = usuarios;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwt = jwt;
    }

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void registrar(@RequestBody @Valid CredenciaisRequest req) {
        if (usuarios.existsByEmail(req.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }
        usuarios.save(new Usuario(req.email(), encoder.encode(req.senha())));
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid CredenciaisRequest req) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.email(), req.senha()));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos");
        }
        return new TokenResponse(jwt.gerar(req.email()));
    }
}
