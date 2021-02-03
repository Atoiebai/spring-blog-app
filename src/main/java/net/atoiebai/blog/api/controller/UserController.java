package net.atoiebai.blog.api.controller;

import lombok.AllArgsConstructor;
import net.atoiebai.blog.api.exception.NoSuchUserException;
import net.atoiebai.blog.model.post.Post;
import net.atoiebai.blog.model.user.BlogUser;
import net.atoiebai.blog.repository.BlogUsersRepository;
import net.atoiebai.blog.service.bloguser.BlogUsersService;
import net.atoiebai.blog.service.post.PostService;
import net.atoiebai.blog.service.registration.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/blog/")
@AllArgsConstructor
public class UserController {

    private final BlogUsersService usersService;
    private final BlogUsersRepository blogUsersRepository;
    private final RegistrationService registrationService;
    private final PostService postService;


    @RequestMapping(value = "/users" ,method = RequestMethod.GET )
    public ResponseEntity<List<BlogUser>> getAllUsers() {
        List<BlogUser> users = usersService.getAllUsers();
        return new ResponseEntity<>(users , HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}" , method = RequestMethod.GET )
    public ResponseEntity<BlogUser> getUser(@PathVariable Long id) {
        BlogUser user = blogUsersRepository.findById(id).orElse(null);
       if(user == null) throw new NoSuchUserException("No user with such id");
       return new ResponseEntity<>(user , HttpStatus.OK);
    }

    @RequestMapping(value = "users/create" , method = RequestMethod.POST)
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

    @RequestMapping(value = "/posts" ,method = RequestMethod.GET )
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts , HttpStatus.OK);
    }

}
