package devdungeon.service;

import devdungeon.domain.CommentVO;
import devdungeon.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final UserService userService;

    @Override
    public int register(CommentVO commentVO) {
        return commentMapper.insert(commentVO);
    }

    @Override
    public List<CommentVO> getListWithPage(int questId, int limit, int offset) {
        return commentMapper.selectByQuestWithPaging(questId, limit, offset)
                .stream()
                .map(this::setAuthorDetails)
                .collect(Collectors.toList());
    }

    @Override
    public CommentVO getReply(int id) {
        return commentMapper.selectOne(id);
    }

    @Override
    public int remove(int id) {

        return commentMapper.delete(id);
    }

    @Override
    public int getTotalNum(int questId) {
        return commentMapper.getTotalNum(questId);
    }

    @Override
    public int modify(CommentVO vo) {
        return commentMapper.update(vo);
    }

    private CommentVO setAuthorDetails(CommentVO commentVO) {
        commentVO.setAuthorDetails(userService.getUser(commentVO.getAuthor()));
        return commentVO;
    }

}
