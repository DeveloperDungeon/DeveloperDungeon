package devdungeon.service;

import devdungeon.domain.CommentVO;

import java.util.List;

public interface CommentService {
    int register(CommentVO commentVO);

    List<CommentVO> getListWithPage(int questId, int limit, int offset);

    int getTotalNum(int questId);

    CommentVO getReply(int id);

    int remove(int id);

    int modify(CommentVO vo);
}
