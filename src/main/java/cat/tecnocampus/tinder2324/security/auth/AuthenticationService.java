package cat.tecnocampus.tinder2324.security.auth;

import cat.tecnocampus.tinder2324.security.configuration.JwtService;
import cat.tecnocampus.tinder2324.security.configuration.UserLabDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserLabDetailsService userLabDetailsService;

  public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager, UserLabDetailsService userLabDetailsService) {
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
    this.userLabDetailsService = userLabDetailsService;
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    UserDetails userDetails = this.userLabDetailsService.loadUserByUsername(request.getUsername());
    var jwtToken = jwtService.generateToken(userDetails);
    AuthenticationResponse response = new AuthenticationResponse();
    response.setAccessToken(jwtToken);

    return response;
  }
}
