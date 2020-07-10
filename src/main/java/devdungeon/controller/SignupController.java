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
public class SignupController {

    private final UserService userService;

    @GetMapping("/signup")
    public String getSignup() {
        return "signUp";
    }

    @PostMapping("/signup")
    public String postSignup(@RequestBody UserVO user) {

        if(!userService.findUser(user.getId())) {
            userService.addUser(user);
            //차후에 loginAPI Merge후  "redirect:/login으로 수정 예정
            return "login";
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Aleady exist");
    }
}
