package devdungeon.controller;

import devdungeon.domain.UserVO;
import devdungeon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
public class SignupAPI {

    private final UserService userService = null;

    @GetMapping("/signup")
    public String getSignup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignup(@RequestBody UserVO user) {

        if(!userService.findUser(user.getId())) {
            userService.addUser(user);
            return "login";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User Aleady exist");
    }
}
