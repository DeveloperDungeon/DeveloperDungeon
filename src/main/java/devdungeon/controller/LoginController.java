package devdungeon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, @RequestParam(value = "prevUrl", required = false, defaultValue = "") String prevUrl) {
        model.addAttribute("loginRequired", !prevUrl.isEmpty());
        return "login";
    }
}
