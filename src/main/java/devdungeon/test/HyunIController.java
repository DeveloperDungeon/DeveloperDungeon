package devdungeon.test;

import devdungeon.domain.QuestVO;
import devdungeon.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hyuni")
public class HyunIController {
    private final QuestService questService;

    @GetMapping("/list")
    public String getList(Model model) {
        List<QuestVO> quests = questService.getAll();
        model.addAttribute("questList", quests);
        return "quest/list";
    }

    @GetMapping("/{id}")
    public String getList(Model model, @PathVariable int id) {
        QuestVO quest = questService.getOne(id);
        model.addAttribute("type", "edit");
        model.addAttribute("quest", quest);
        return "quest/write";
    }
}
