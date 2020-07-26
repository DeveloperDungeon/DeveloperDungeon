package devdungeon.controller;

import devdungeon.domain.SearchVO;
import devdungeon.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class QuestSearchController {

    private final SearchService ss;

    @GetMapping
    public String getSearchQuest(Model model, @RequestParam("q") String text, @RequestParam("type") String method) throws Exception {
        SearchVO searchVO = new SearchVO(method, text);

        if (method.equals("title")) {
            model.addAttribute("questList", ss.searchByTitle(text));
        } else if (method.equals("content")) {
            model.addAttribute("questList",ss.searchByContent(text));
        } else if (method.equals(("author"))) {
            model.addAttribute("questList",ss.searchByAuthor(text));
        } else if (method.equals("title&content")) {
            model.addAttribute("questList", ss.searchByTitleContent(text));
        }
        //model.addAttribute("searchInfo", searchVO);
        return "search";
    }
}
