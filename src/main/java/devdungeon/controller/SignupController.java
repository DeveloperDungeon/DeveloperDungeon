package devdungeon.controller;

import devdungeon.domain.UserVO;
import devdungeon.service.UserService;
import groovy.grape.GrapeIvy;
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

    private int IdMinLength = 8;
    private int IdMaxLength = 20;

    private int NickNameMinLength = 3;
    private int NickNameMaxLength = 25;

    private String EmailRegExp = "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+$";
    private String PasswordRegExp = "^(?=.*[0-9])(?=.*[a-z]).{8,20}$";

    @GetMapping("/signup")
    public String getSignup() {
        return "signUp";
    }

    @PostMapping("/signup")
    public String postSignup(@RequestBody UserVO user) {
        //check id
        if (user.getId().length() < IdMinLength || user.getId().length() > IdMaxLength) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Too long or too short ID");
        } else if (userService.findUser(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Same id already exist");
        }
        //check password
        else if (!user.getPassword().matches(PasswordRegExp)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid password");
        }
        //check nickname
        else if (user.getNickName().length() < NickNameMinLength || user.getNickName().length() > NickNameMaxLength) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Too long or too short nickname");
        } else if (userService.findUserByNick(user.getNickName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Same nickname already exist");
        }
        //check email
        else if (!user.getEmail().matches(EmailRegExp)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email");
        } else if (userService.findUserByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Same email already exist");
        } else {
            userService.addUser(user);
            return "redirect:/login";
        }

    }
}
