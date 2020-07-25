package devdungeon.domain;

import lombok.Data;

import java.util.List;

@Data
public class CommentPageVO {
    private List<CommentVO> list;
    private PageVO pageVO;

    public CommentPageVO(List<CommentVO> list, PageVO pageVO) {
        this.list = list;
        this.pageVO = pageVO;
    }
}
