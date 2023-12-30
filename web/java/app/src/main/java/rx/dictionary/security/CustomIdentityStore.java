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
        if (credential.compareTo("user","user")) {
            System.out.println("::::::::::::::::::::::");
            return new CredentialValidationResult("user", Set.of("viewer"));
        } else if (credential.compareTo("admin","admin")) {
            System.out.println("::::::::::::::::::::::");
            return new CredentialValidationResult("admin", Set.of("viewer","updater"));
        }
        return CredentialValidationResult.INVALID_RESULT;
    }
}
