package devdungeon.controller;

import devdungeon.annotation.AuthAnnotation;
import devdungeon.annotation.CertifyAnnotation;
import devdungeon.domain.PageVO;
import devdungeon.domain.QuestVO;
import devdungeon.template.RedirectBody;
import devdungeon.service.QuestService;
import devdungeon.template.ResponseTemplate;
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
    @ResponseBody
    public ResponseTemplate<RedirectBody> postQuestWrite(@RequestBody QuestVO questVO) {
        questVO.setAuthor((String) session.getAttribute("user"));
        questService.addQuest(questVO);
        return new ResponseTemplate<>(ResponseTemplate.Code.REDIRECT, new RedirectBody("success", "quest"));
    }

    @GetMapping("/edit/{id}")
    @AuthAnnotation
    public String getQuestEdit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("quest", questService.getOne(id));
        model.addAttribute("type", "edit");
        return "quest/write";
    }

    @PutMapping("/{id}")
    @AuthAnnotation
    @ResponseBody
    public ResponseTemplate<RedirectBody> putQuestEdit(@PathVariable("id") Integer id, @RequestBody QuestVO questVO) {
        questVO.setId(id);
        if (questService.editQuest(questVO) == 1) {
            return new ResponseTemplate<>(ResponseTemplate.Code.REDIRECT, new RedirectBody("success", "quest/" + id));
        }
        return new ResponseTemplate<>(ResponseTemplate.Code.REDIRECT, new RedirectBody("fail", "quest/" + id));
    }

    @DeleteMapping("/{id}")
    @AuthAnnotation
    @ResponseBody
    public ResponseTemplate<RedirectBody> deleteQuestRemove(@PathVariable("id") int id) {
        if (questService.remove(id) == 1)
            return new ResponseTemplate<>(ResponseTemplate.Code.REDIRECT, new RedirectBody("success", "quest"));
        else return new ResponseTemplate<>(ResponseTemplate.Code.REDIRECT, new RedirectBody("fail", "quest"));
    }
}
