package devdungeon.controller;


import devdungeon.domain.LoginVO;
import devdungeon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class LoginAPI {

    private final UserService userService = null;

    @PostMapping("/login")
    public String postLogin(@RequestBody LoginVO user){
		if(userService.findUser(user.getId()) && (user.getPassword()==userService.getUserPassword(user.getId())))
			return "loginsuccess";
		return "loginfail";
    	
    }
}
