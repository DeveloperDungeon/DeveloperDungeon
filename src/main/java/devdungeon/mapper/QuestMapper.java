package devdungeon.mapper;

import devdungeon.domain.QuestVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestMapper {
    @Select("SELECT * FROM quest")
    List<QuestVO> selectAll();

    @Select("SELECT * FROM quest WHERE id=#{id}")
    QuestVO selectOne(int id);

    @Select("SELECT * FROM quest ORDER BY reg_time DESC LIMIT #{amount}")
    List<QuestVO> selectRecent(int amount);

    @Insert("INSERT INTO quest(title,content,author,reg_time) VALUES(#{title},#{content},#{author},#{regTime})")
    int insertQuest(QuestVO questVO);
}
