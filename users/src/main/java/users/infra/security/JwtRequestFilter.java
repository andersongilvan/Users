package users.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        // obtem o valor do cabeçalho "Authorization" da requisição
        String authorizationHeader = request.getHeader("Authorization");

        // verifica se o acbeçalho existe e começa com "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // extrai o token do cabeçalho
            String token = authorizationHeader.substring(7);

            // extrai o nome de usuario do token jwt
            String username = jwtUtil.extractUsername(token);

            // se o nome de usuario não for nulo e o usuario não estiver autenticado ainda
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // carrega os detalhes do usuario a partir do username do usuario
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // valida o token JWT
                if (jwtUtil.validateToken(token, username)) {
                    // criaq um objeto de autenticacao do usuario
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );


                    // define a autenticação no contexto de segurança
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }
            }

        }

        // continua a cadeia de filtros permitindo que a requisição prosiga
        filterChain.doFilter(request, response);

    }
}
