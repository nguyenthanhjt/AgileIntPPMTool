package io.agileintelligence.ppmtool.web;

import io.agileintelligence.ppmtool.domain.User;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    MapValidationErrorService validationErrorService;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        // Validate password match

        ResponseEntity<?> errorMap = validationErrorService.validateError(result);

        if (null != errorMap) return errorMap;

        User newUser = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(newUser);

    }
}
