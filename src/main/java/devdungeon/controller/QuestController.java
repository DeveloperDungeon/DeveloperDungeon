package devdungeon.controller;

import devdungeon.annotation.AuthAnnotation;
import devdungeon.annotation.CertifyAnnotation;
import devdungeon.domain.PageVO;
import devdungeon.domain.QuestVO;
import devdungeon.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quest")
public class QuestController {

    private final QuestService questService;
    private final HttpSession session;

    @GetMapping
    public String getQuestList(Model model, @RequestParam(value = "page", required = false,
            defaultValue = "1") int page) {

        PageVO pageVO = new PageVO(page, questService.getTotalQuestNum());
        model.addAttribute("pageInfo", pageVO);
        model.addAttribute("questList",
                questService.getQuestWithPage(PageVO.PER_PAGE, pageVO.getOffset()));
        return "quest/list";
    }

    @GetMapping("/{id}")
    public String getQuest(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("quest", questService.getOne(id));
        return "quest/view";
    }

    @GetMapping("/write")
    @CertifyAnnotation
    public String getQuestWrite() {
        return "quest/write";
    }

    @PostMapping("/write")
    @CertifyAnnotation
    public String postQuestWrite(@RequestBody QuestVO questVO) {
        questVO.setAuthor((String) session.getAttribute("user"));
        questService.addQuest(questVO);
        return "redirect:/quest";
    }

    @GetMapping("/edit/{id}")
    @AuthAnnotation
    public String getQuestEdit(Model model, @PathVariable("id") Integer id) {
        String sessUser = (String) session.getAttribute("user");
        String questAuthor = questService.getOne(id).getAuthor();
        if (sessUser.equals(questAuthor)) {
            model.addAttribute("quest", questService.getOne(id));
            model.addAttribute("type", "edit");
            return "quest/write";
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No authentication to edit this quest");

    }

    @PutMapping("/edit/{id}")
    @AuthAnnotation
    public String putQuestEdit(@PathVariable("id") Integer id, @RequestBody QuestVO questVO) {
        String sessUser = (String) session.getAttribute("user");
        String questAuthor = questService.getOne(id).getAuthor();
        if (sessUser.equals(questAuthor)) {
            questVO.setId(id);
            questService.editQuest(questVO);
            return "redirect:/quest/" + id;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No authentication to edit this quest");
    }

    @DeleteMapping("/remove/{id}")
    @AuthAnnotation
    public String DeleteQuestRemove(RedirectAttributes redirectAttributes, @PathVariable("id") int id) {
        if (questService.remove(id) == 1)
            redirectAttributes.addFlashAttribute("result", "success");
        else redirectAttributes.addFlashAttribute("result", "fail");

        return "redirect:/quest";
    }
}
