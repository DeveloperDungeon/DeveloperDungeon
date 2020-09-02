package devdungeon.service;

import devdungeon.domain.ChapterVO;

import java.util.List;

public interface ChapterService {
    int addChapter(ChapterVO chapterVO, String userId);

    List<ChapterVO> findWritableChapters(String userId);

    ChapterVO findChapter(int id);

    int removeChapter(int id);
}
