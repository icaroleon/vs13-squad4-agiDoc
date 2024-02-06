package br.com.agidoc.agiDoc.service;


import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    static final String HEADER_STRING = "Authorization";
    private final UserService userService;

    public String getToken(User user) {
        String tokenTexto = user.getUser() + ";" + user.getPassword();
        String token = Base64.getEncoder().encodeToString(tokenTexto.getBytes());
        return token;
    }

    // token = yeAGieha9eH(E8 = mari;123
    public Optional<User> isValid(String token) throws RegraDeNegocioException{
        if (token == null) {
            return Optional.empty();
        }
        byte[] decodedBytes = Base64.getUrlDecoder().decode(token);
        String decoded = new String(decodedBytes);
        String[] split = decoded.split(";");
        return userService.login(split[0], split[1]);
    }
}