package devdungeon.service;

import devdungeon.domain.ChapterVO;
import devdungeon.mapper.ChapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterMapper chapterMapper;

    @Override
    public int addChapter(ChapterVO chapterVO, String userId) {
        int count = chapterMapper.insertChapter(chapterVO);
        int id = chapterVO.getId();

        if (chapterVO.getIsPublic() == 0) chapterMapper.insertWhitelist(Collections.singletonList(userId), id);
        return count;
    }

    @Override
    public List<ChapterVO> findWritableChapters(String userId) {
        List<ChapterVO> writableChapters = new ArrayList<>();

        writableChapters.addAll(chapterMapper.selectPrivateWritableChapter(userId));
        writableChapters.addAll(chapterMapper.selectPublicWritableChapter());
        return writableChapters;
    }
}
