package net.atoiebai.blog.api.controller;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.model.user.BlogUser;
import net.atoiebai.blog.service.bloguser.BlogUsersService;
import net.atoiebai.blog.service.registration.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/blog/users")
@AllArgsConstructor
public class UserController {

    private final BlogUsersService usersService;
    private final RegistrationService registrationService;

    @RequestMapping( method = RequestMethod.GET )
    public ResponseEntity<List<BlogUser>> getAllUsers() {
        List<BlogUser> users = usersService.getAllUsers();
        return new ResponseEntity<>(users , HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET )
    public ResponseEntity<BlogUser> getUser(@PathVariable Long id) {
        BlogUser user = usersService.getUser(id);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }

    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public ResponseEntity<String> createUser(
            @RequestBody @Valid BlogUser user,
            BindingResult bindingResult) {

        if (usersService.userExist(user.getEmail()) ||
                usersService.userExist(user.getUsername())) {
            bindingResult.addError(new FieldError("user", "email", "email or username already in use | почта или юзернейм уже используется"));
        }

        if(bindingResult.hasErrors()) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        registrationService.register(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
