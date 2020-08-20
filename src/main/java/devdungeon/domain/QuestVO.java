package devdungeon.domain;

import lombok.Data;

@Data
public class QuestVO {
    private int id;
    private String title;
    private String content;
    private String author;
    private long regTime;
    private Integer chapterId;

    private UserVO authorDetails;
}
