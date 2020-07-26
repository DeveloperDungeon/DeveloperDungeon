package devdungeon.controller;

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
    public String getSearchQuest(Model model, @RequestParam("q") String text, @RequestParam("type") String method) {

        switch (method) {
            case "title":
                model.addAttribute("questList", ss.searchByTitle(text));
                break;
            case "content":
                model.addAttribute("questList", ss.searchByContent(text));
                break;
            case "author":
                model.addAttribute("questList", ss.searchByAuthor(text));
                break;
            case "title&content":
                model.addAttribute("questList", ss.searchByTitleContent(text));
                break;
        }
        //model.addAttribute("searchInfo", searchVO);
        return "search";
    }
}
