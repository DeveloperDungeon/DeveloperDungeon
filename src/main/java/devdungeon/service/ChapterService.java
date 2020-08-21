package devdungeon.service;

import devdungeon.domain.ChapterVO;

import java.util.List;

public interface ChapterService {
    int addChapter(ChapterVO chapterVO, boolean isPublic, String userId);

    List<ChapterVO> findChapters(String userId);
}
