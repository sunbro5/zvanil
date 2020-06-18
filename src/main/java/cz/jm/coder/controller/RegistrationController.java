package cz.jm.coder.controller;

import cz.jm.coder.model.User;
import cz.jm.coder.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void register(@Validated User user){
        registrationService.registerNewUser(user);
    }
}
