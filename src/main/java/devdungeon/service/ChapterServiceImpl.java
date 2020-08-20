package devdungeon.service;

import devdungeon.domain.ChapterVO;
import devdungeon.mapper.ChapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterMapper chapterMapper;

    @Override
    public int addChapter(ChapterVO chapterVO, boolean isPublic, String userId) {
        int count = chapterMapper.insertChapter(chapterVO);
        int id = chapterVO.getId();

        if (!isPublic) chapterMapper.insertWhitelist(Arrays.asList(userId), id);
        else chapterMapper.insertAllWhiteList(id);
        return count;
    }

    @Override
    public List<ChapterVO> findChapters(String userId) {
        return chapterMapper.selectWritableChapter(userId);
    }
}
