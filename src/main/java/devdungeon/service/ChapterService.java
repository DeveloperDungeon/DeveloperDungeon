package devdungeon.service;

import devdungeon.domain.ChapterVO;

import java.util.List;

public interface ChapterService {
    int addChapter(ChapterVO chapterVO, String userId);

    List<ChapterVO> getWritableChapters(String userId);

    ChapterVO getChapter(int id);

    int removeChapter(int id);
}
