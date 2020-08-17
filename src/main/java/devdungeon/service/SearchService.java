package devdungeon.service;

import devdungeon.domain.QuestVO;

import java.util.List;

public interface SearchService {
    List<QuestVO> searchByAuthor(String text);

    List<QuestVO> searchByContent(String text);

    List<QuestVO> searchByTitle(String text);

    List<QuestVO> searchByTitleContent(String text);
}
