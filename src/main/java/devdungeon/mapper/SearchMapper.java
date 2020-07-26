package devdungeon.mapper;

import devdungeon.domain.QuestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SearchMapper {
    @Select("SELECT * FROM quest WHERE author LIKE %{text}%")
    List<QuestVO> searchByAuthor(String text);

    @Select("SELECT * FROM quest WHERE content LIKE %{text}%")
    List<QuestVO> searchByContent(String text);

    @Select("SELECT * FROM quest WHERE title LIKE %{text}%")
    List<QuestVO> searchByTitle(String text);

    @Select("SELECT * FROM quest WHERE (title LIKE %{text}% OR content LIKE %{text}%)")
    List<QuestVO> searchByTitleContent(String text);
}
