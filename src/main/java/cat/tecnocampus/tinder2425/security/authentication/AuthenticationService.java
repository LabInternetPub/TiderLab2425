package cat.tecnocampus.tinder2425.security.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;


@Service
public class AuthenticationService {
  private final AuthenticationManager authenticationManager;
  private final UserLabDetailsService userLabDetailsService;
  private final JwtEncoder jwtEncoder;

  public AuthenticationService(AuthenticationManager authenticationManager, UserLabDetailsService userLabDetailsService, JwtEncoder jwtEncoder) {
    this.authenticationManager = authenticationManager;
    this.userLabDetailsService = userLabDetailsService;
    this.jwtEncoder = jwtEncoder;
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.username(),
            request.password()
        )
    );

    var jwtToken = generateToken(authentication);
    AuthenticationResponse response = new AuthenticationResponse();
    response.setAccessToken(jwtToken);

    return response;
  }

  public String generateToken(Authentication authentication) {
    Instant now = Instant.now();

    System.out.println("Authorities: " + authentication.getAuthorities()
            .stream().map(GrantedAuthority::getAuthority).toList());
    String scope = authentication.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

    JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(10, ChronoUnit.HOURS))
            .subject(authentication.getName())
            .claim("scope", scope)
            .build();

    var encoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), claims);
    return jwtEncoder.encode(encoderParameters).getTokenValue();
  }

}
