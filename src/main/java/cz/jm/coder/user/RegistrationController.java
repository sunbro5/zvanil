package cz.jm.coder.user;

import cz.jm.coder.security.model.UserRegistrationRequest;
import cz.jm.coder.user.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void register(@Valid @RequestBody UserRegistrationRequest registrationRequest){
        registrationService.registerNewUser(registrationRequest);
    }
}
