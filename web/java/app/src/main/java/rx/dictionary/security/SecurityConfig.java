package rx.dictionary.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

@Named
@ApplicationScoped
@BasicAuthenticationMechanismDefinition(realmName="local")
public class SecurityConfig {
}
