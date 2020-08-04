package devdungeon.api;

import devdungeon.domain.UserVO;
import devdungeon.httperror.SignUpException;
import devdungeon.service.UserService;
import devdungeon.template.RedirectTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpAPI {
    private final UserService userService;

    @GetMapping
    public String getSignUp() {
        return "signUp";
    }

    @PostMapping
    public ResponseEntity<RedirectTemplate> postSignUp(@RequestBody UserVO user) throws SignUpException {
        //check id
        int idMinLength = 8;
        int idMaxLength = 20;
        int nickNameMinLength = 3;
        int nickNameMaxLength = 25;
        String emailRegExp = "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+$";
        String passwordRegExp = "^(?=.*[0-9])(?=.*[a-z]).{8,20}$";


        if (user.getId().length() < idMinLength || user.getId().length() > idMaxLength) {
            throw new SignUpException(601, "Too long or too short ID");
        } else if (userService.findUser(user.getId())) {
            throw new SignUpException(602, "Same id already exist");
        }
        //check password
        else if (!user.getPassword().matches(passwordRegExp)) {
            throw new SignUpException(603, "Invalid password");
        }
        //check nickname
        else if (user.getNickName().length() < nickNameMinLength || user.getNickName().length() > nickNameMaxLength) {
            throw new SignUpException(604, "Too long or too short nickname");
        } else if (userService.findUserByNick(user.getNickName())) {
            throw new SignUpException(605, "Same nickname already exist");
        }
        //check email
        else if (!user.getEmail().matches(emailRegExp)) {
            throw new SignUpException(606, "Invalid email");
        } else if (userService.findUserByEmail(user.getEmail())) {
            throw new SignUpException(607, "Same email already exist");
        } else {
            userService.addUser(user);
            return new ResponseEntity<>(new RedirectTemplate("/login"), HttpStatus.MULTIPLE_CHOICES);
        }

    }

    @ExceptionHandler(SignUpException.class)
    public ResponseEntity<SignUpException> handleSignUpException(SignUpException suex) {
        System.out.println(suex.getErrCode());
        return new ResponseEntity<>(suex, HttpStatus.BAD_REQUEST);
    }
}
