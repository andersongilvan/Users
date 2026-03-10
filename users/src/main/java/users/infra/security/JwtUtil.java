package users.infra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtUtil {

    // chave secreta usada para assinar e verificar o token JWT
    private final SecretKey secretKey;

    public JwtUtil() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    // gera o token JWT com o nom,e de usuario e validade de 1 hora
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // define o nome de usuario como o assunto do token
                .setIssuedAt(new Date()) // define a data e hora de emissão do token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // define a data e hora de
                // expiração  (1hora a partir da emissão)
                .signWith(secretKey) // assina o token com a chave secreta
                .compact(); // constroi o jwt
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey) // define a chave secreta para validar a assinatura do token
                .build()
                .parseClaimsJws(token) // analiza o jwt e obtem as claims
                .getBody(); // retorna o corpo das claims
    }

    // extrai o nome do susario do token jwt
    public String extractUsername(String token) {
        // obtem o assunto (nome do usuario) das claims
        return extractClaims(token).getSubject();
    }

    // verifica se o token esta expirado
    public boolean isExpired(String token) {
        // compara a data de expiracao do token com a data atual
        return extractClaims(token).getExpiration().before(new Date());
    }

    // valida o token jwt verificando o username do usuario e se o token não está expirado
    public boolean validateToken(String token, String username) {
        // extrai o nome do usuario do token
        String extractedUsername = extractUsername(token);

        // verifica se o nome do usuario do token corresponde ao fornecido e se o token não esta expirado
        return extractedUsername.equals(username) && !isExpired(token);
    }
}
