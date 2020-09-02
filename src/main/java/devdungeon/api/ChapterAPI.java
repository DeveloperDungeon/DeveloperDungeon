package devdungeon.api;

import devdungeon.api.annotation.ApiCertifyAnnotation;
import devdungeon.domain.ChapterVO;
import devdungeon.service.ChapterService;
import devdungeon.template.RedirectTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chapter")
public class ChapterAPI {

    private final ChapterService chapterService;
    private final HttpSession session;

    @PostMapping
    @ApiCertifyAnnotation
    public ResponseEntity<RedirectTemplate> postChapterWrite(@RequestBody ChapterVO chapterVO) {
        //ret는 저장 성공여부..
        int ret = chapterService.addChapter(chapterVO, (String) session.getAttribute("user"));
        return new ResponseEntity<>(new RedirectTemplate("/chapter/" + chapterVO.getId()), HttpStatus.MULTIPLE_CHOICES);
    }

    @GetMapping
    @ApiCertifyAnnotation
    public ResponseEntity<List<ChapterVO>> getWritableChapter(@RequestParam(value = "writeable", required = false, defaultValue = "false")
                                                                      boolean writable) {
        if (writable) {
            String userId = (String) session.getAttribute("user");
            List<ChapterVO> chapterList = chapterService.findWritableChapters(userId);
            return new ResponseEntity<>(chapterList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiCertifyAnnotation
    public ResponseEntity<Object> deleteChapter(@PathVariable("id") int id) {
        if (chapterService.findChapter(id) == null) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        String userId = (String) session.getAttribute("user");
        List<ChapterVO> chapterList = chapterService.findWritableChapters(userId);
        boolean removable = false;
        for (ChapterVO c : chapterList) {
            if (c.getId() == id) {
                removable = true;
                break;
            }
        }
        if (removable) {
            chapterService.removeChapter(id);
            return new ResponseEntity<>(new RedirectTemplate("/"), HttpStatus.MULTIPLE_CHOICES);
        }
        return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
    }
}
