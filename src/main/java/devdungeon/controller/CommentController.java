package devdungeon.controller;

import devdungeon.annotation.AuthAnnotation;
import devdungeon.annotation.CertifyAnnotation;
import devdungeon.domain.CommentPageVO;
import devdungeon.domain.CommentVO;
import devdungeon.domain.PageVO;
import devdungeon.service.CommentService;
import devdungeon.template.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final HttpSession session;

    @PostMapping
    @CertifyAnnotation
    public ResponseTemplate<String> postReplyRegister(@RequestBody CommentVO commentVO) {
        commentVO.setAuthor((String) session.getAttribute("user"));
        return commentService.register(commentVO) == 1 ? new ResponseTemplate<>(ResponseTemplate.Code.OK, "success") :
                new ResponseTemplate<>(ResponseTemplate.Code.BAD_REQUEST, "fail");
    }

    @GetMapping
    public ResponseTemplate<CommentPageVO> getListWithPage(@RequestParam("questId") int questId,
                                                           @RequestParam(value = "page", required = false, defaultValue = "1")
                                                           int page) {
        PageVO pageVO = new PageVO(page, commentService.getTotalNum(questId));

        return new ResponseTemplate<>(ResponseTemplate.Code.OK, new CommentPageVO(commentService.getListWithPage(
                questId, PageVO.PER_PAGE, pageVO.getOffset()), pageVO));
    }

    @GetMapping("/{id}")
    public ResponseTemplate<CommentVO> putReplyModify(@PathVariable("id") int id) {
        return new ResponseTemplate<>(ResponseTemplate.Code.OK, commentService.getReply(id));
    }

    @DeleteMapping("/{id}")
    @AuthAnnotation
    public ResponseTemplate<String> remove(@PathVariable("id") int id) {
        return commentService.remove(id) == 1 ? new ResponseTemplate<>(ResponseTemplate.Code.OK, "success") :
                new ResponseTemplate<>(ResponseTemplate.Code.BAD_REQUEST, "success");
    }

    @PutMapping("/{id}")
    @AuthAnnotation
    public ResponseTemplate<String> modify(@PathVariable("id") int id, @RequestBody CommentVO commentVO) {
        commentVO.setId(id);
        return commentService.modify(commentVO) == 1 ? new ResponseTemplate<>(ResponseTemplate.Code.OK, "success") :
                new ResponseTemplate<>(ResponseTemplate.Code.BAD_REQUEST, "success");
    }
}
