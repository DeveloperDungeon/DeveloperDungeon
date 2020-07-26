package devdungeon.controller;

import devdungeon.domain.QuestVO;
import devdungeon.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final QuestService questService;

    private int counter = 0;

    @GetMapping("/test")
    public String test() {
        System.out.println("들어왔습니다! " + counter++);
        return "index";
    }

    @GetMapping("/")
    public String welcomeToMyHouse(Model model) {
        List<QuestVO> questList = questService.getRecent(2);
        model.addAttribute("questList", questList);
        return "index";
    }

}
