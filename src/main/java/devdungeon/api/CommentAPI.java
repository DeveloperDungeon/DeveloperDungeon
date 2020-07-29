package devdungeon.api;

import devdungeon.annotation.ApiAuthAnnotation;
import devdungeon.annotation.ApiCertifyAnnotation;
import devdungeon.domain.CommentPageVO;
import devdungeon.domain.CommentVO;
import devdungeon.domain.PageVO;
import devdungeon.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentAPI {

    private final CommentService commentService;
    private final HttpSession session;

    @PostMapping
    @ApiCertifyAnnotation
    public ResponseEntity<String> postReplyRegister(@RequestBody CommentVO commentVO) {
        commentVO.setAuthor((String) session.getAttribute("user"));
        return commentService.register(commentVO) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) :
                new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<CommentPageVO> getListWithPage(@RequestParam("questId") int questId,
                                                         @RequestParam(
                                                                 value = "page",
                                                                 required = false,
                                                                 defaultValue = "1")
                                                                 int page) {
        PageVO pageVO = new PageVO(page, commentService.getTotalNum(questId));

        return new ResponseEntity<>(new CommentPageVO(commentService.getListWithPage(questId, PageVO.PER_PAGE, pageVO.getOffset()),
                pageVO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentVO> putReplyModify(@PathVariable("id") int id) {
        return new ResponseEntity<>(commentService.getReply(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiAuthAnnotation
    public ResponseEntity<String> remove(@PathVariable("id") int id) {
        return commentService.remove(id) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) :
                new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    @ApiAuthAnnotation
    public ResponseEntity<String> modify(@PathVariable("id") int id, @RequestBody CommentVO commentVO) {
        commentVO.setId(id);
        return commentService.modify(commentVO) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) :
                new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
