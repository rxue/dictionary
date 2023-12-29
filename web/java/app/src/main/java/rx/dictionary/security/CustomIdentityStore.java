package rx.dictionary.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Set;

@Named
@ApplicationScoped
public class CustomIdentityStore implements IdentityStore {

    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
        if (credential.compareTo("x","y")) {
            System.out.println("::::::::::::::::::::::");
            return new CredentialValidationResult("x", Set.of("admin"));
        }
        return CredentialValidationResult.INVALID_RESULT;
    }
}
