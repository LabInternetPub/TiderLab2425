package cat.tecnocampus.tinder2425.security.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final UserLabDetailsService userLabDetailsService;
  private final JwtService jwtService;

  @Value("${application.security.jwt.token-prefix}")
  private String tokenPrefix;

  public JwtAuthenticationFilter(UserLabDetailsService userLabDetailsService, JwtService jwtService) {
    this.userLabDetailsService = userLabDetailsService;
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {

    final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String jwt;
    final String username;

    if (authorizationHeader == null ||!authorizationHeader.startsWith(tokenPrefix)) {
      filterChain.doFilter(request, response);
      return;
    }

    jwt = authorizationHeader.replace(tokenPrefix, "");
    username = jwtService.extractUsername(jwt);
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userLabDetailsService.loadUserByUsername(username);
      if (jwtService.isTokenValid(jwt, userDetails)) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
