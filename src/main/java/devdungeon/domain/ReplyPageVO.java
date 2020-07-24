package devdungeon.domain;

import lombok.Data;

import java.util.List;

@Data
public class ReplyPageVO {
    private List<ReplyVO> list;
    private PageVO pageVO;

    public ReplyPageVO(List<ReplyVO> list, PageVO pageVO) {
        this.list = list;
        this.pageVO = pageVO;
    }
}
