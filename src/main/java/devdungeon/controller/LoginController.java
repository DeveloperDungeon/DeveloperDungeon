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
public class LoginController {

    private final UserService userService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String postLogin(@RequestBody UserVO user){
		if(userService.findUser(user.getId(),user.getPassword())) {
            return "redirect:/";
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong user id or password");
    	
    }
}
