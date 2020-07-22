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

    @GetMapping("/signup")
    public String getSignup() {
        return "signUp";
    }

    @PostMapping("/signup")
    public String postSignup(@RequestBody UserVO user) {
        //check id
        int idMinLength = 8;
        int idMaxLength = 20;
        int nickNameMinLength = 3;
        int nickNameMaxLength = 25;
        String emailRegExp = "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+$";
        String passwordRegExp = "^(?=.*[0-9])(?=.*[a-z]).{8,20}$";
        if (user.getId().length() < idMinLength || user.getId().length() > idMaxLength) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Too long or too short ID");
        } else if (userService.findUser(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Same id already exist");
        }
        //check password
        else if (!user.getPassword().matches(passwordRegExp)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid password");
        }
        //check nickname
        else if (user.getNickName().length() < nickNameMinLength || user.getNickName().length() > nickNameMaxLength) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Too long or too short nickname");
        } else if (userService.findUserByNick(user.getNickName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Same nickname already exist");
        }
        //check email
        else if (!user.getEmail().matches(emailRegExp)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email");
        } else if (userService.findUserByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Same email already exist");
        } else {
            userService.addUser(user);
            return "redirect:/login";
        }

    }
}
