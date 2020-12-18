package cz.jm.coder.user;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;

@Component
public class LoggedUserFacade {

    public Optional<Authentication> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.of(authentication);
        }
        return Optional.empty();
    }

    public String getUserUsername(){
        return getUser().map(Principal::getName).orElse(null);
    }
}
