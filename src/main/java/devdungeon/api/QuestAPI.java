package devdungeon.api;

import devdungeon.api.annotation.ApiAuthAnnotation;
import devdungeon.api.annotation.ApiCertifyAnnotation;
import devdungeon.domain.QuestVO;
import devdungeon.service.ChapterService;
import devdungeon.service.QuestService;
import devdungeon.template.RedirectTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quest")
public class QuestAPI {

    private final QuestService questService;
    private final HttpSession session;
    private final ChapterService chapterService;

    @PostMapping
    @ApiCertifyAnnotation
    public ResponseEntity<RedirectTemplate> postQuestWrite(@RequestBody QuestVO questVO) {
        String curAuthor = (String) session.getAttribute("user");
        questVO.setAuthor(curAuthor);
        questService.addQuest(questVO);
        return new ResponseEntity<>(new RedirectTemplate("/quest"), HttpStatus.MULTIPLE_CHOICES);
    }

    @PutMapping("/{id}")
    @ApiAuthAnnotation
    public ResponseEntity<RedirectTemplate> putQuestEdit(@PathVariable("id") int id, @RequestBody QuestVO questVO) {
        questVO.setId(id);
        questService.editQuest(questVO);
        return new ResponseEntity<>(new RedirectTemplate("/quest/" + id), HttpStatus.MULTIPLE_CHOICES);
    }

    @DeleteMapping("/{id}")
    @ApiAuthAnnotation
    public ResponseEntity<Object> deleteQuestRemove(@PathVariable("id") int id) {
        if (questService.remove(id) == 1)
            return new ResponseEntity<>(new RedirectTemplate("/quest"), HttpStatus.MULTIPLE_CHOICES);
        else return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
    }
}
