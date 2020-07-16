package devdungeon.controller;

import devdungeon.annotation.CertifyAnnotation;
import devdungeon.domain.PageDTO;
import devdungeon.domain.QuestVO;
import devdungeon.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quest")
public class QuestController {

    private final QuestService questService;
    private final HttpSession session;

    @GetMapping("/list")
    public void getQuestList(Model model, @RequestParam(value = "page", required = false,
            defaultValue = "1") int page) {

        PageDTO pageDTO = new PageDTO(page, questService.getTotalQuestNum());
        model.addAttribute("pageInfo",pageDTO);
        model.addAttribute("questList",
                questService.getQuestWithPage(pageDTO.getLimit(), pageDTO.getOffset()));
    }

    @GetMapping("/{id}")
    public void getQuest(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("quest", questService.getOne(id));
    }

    @GetMapping("/write")
    @CertifyAnnotation
    public String getQuestWrite() {
        return "write";
    }

    @PostMapping("/write")
    @CertifyAnnotation
    public String postQuestWrite(@RequestBody QuestVO questVO) {
        questVO.setAuthor((String) session.getAttribute("user"));
        questService.addQuest(questVO);
        return "redirect:/quest/list";
    }

    @GetMapping("/edit")
    @CertifyAnnotation
    public String getQuestEdit() {
        return "questEdit";
    }

    @PutMapping("/edit")
    @CertifyAnnotation
    public String putQuestEdit(@RequestBody QuestVO questVO) {
        questService.editQuest(questVO);
        return "questEdit";
    }
}
