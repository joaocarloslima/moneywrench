package br.com.fiap.moneywrench.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.fiap.moneywrench.model.Usuario;
import br.com.fiap.moneywrench.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        

                // obter o token
                var token = getToken(request);
                log.info(token);

                // if existe um token
                if(token != null){
                    Usuario user = tokenService.validateToken(token);
                    Authentication auth = user.toAuthentication();
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

                // chamar o próximo filtro
                filterChain.doFilter(request, response);


    }

    private String getToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization"); 
        log.info("Header = {}", header);

        if (header == null || !header.startsWith("Bearer ")) return null;

        var token = header.replace("Bearer ", "");

        return token;
    }


    
}
