package devdungeon.controller;

import devdungeon.domain.QuestVO;
import devdungeon.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quest/*")
public class QuestController {

    private final QuestService questService;

    @GetMapping("/list")
    public void getQuestList(Model model) {
        model.addAttribute("list",questService.getAll());
    }

    @GetMapping("/write")
    public String getQuestWrite() {
        return "questWrite";
    }

    @PostMapping("/write")
    public String postQuestWrite(@RequestBody QuestVO questVO) {
        questVO.setAuthor("asdf");
        questService.addQuest(questVO);
        return "redirect:/quest/list";
    }

}
