package application.application.configuration.Security;

import application.application.model.AppUser;
import application.application.service.IAppUserService;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {
    private final IAppUserService appUserService;
    private AuthenticationManager authenticationManager;
    private JwtEncoder jwtEncoder;

    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }

    @PostMapping("/login")
    public Map<String, String> login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        AppUser appUser = appUserService.getAppUserByName(username);
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                .subject(username)
                .claim("subId", appUser.getId())
                .claim("email", appUser.getEmail())
                .claim("authorities", authorities)
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS512).build(),
                jwtClaimsSet);
        Jwt jwt = jwtEncoder.encode(jwtEncoderParameters);
        String jwtString = jwt.getTokenValue();
        return Map.of("access_token", jwtString);
    }
}
