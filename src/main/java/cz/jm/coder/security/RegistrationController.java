package cz.jm.coder.security;

import cz.jm.coder.security.model.UserRegistrationRequest;
import cz.jm.coder.security.service.RegistrationService;
import cz.jm.coder.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
