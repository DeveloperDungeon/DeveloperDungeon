package devdungeon.domain;

import lombok.Data;

@Data
public class CommentVO {
    private int id;
    private int questId;
    private String author;
    private String content;
    private long regTime;

    CommentVO(String content) {
        this.content = content;
    }

    private UserVO authorDetails;
}
