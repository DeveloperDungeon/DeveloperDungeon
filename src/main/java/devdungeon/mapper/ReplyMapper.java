package devdungeon.mapper;

import devdungeon.domain.ReplyVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReplyMapper {
    @Insert("INSERT INTO reply(quest_id,author,content,reg_time) VALUES(#{questId},#{author},#{content},#{regTime})")
    int insert(ReplyVO replyVO);

    @Select("SELECT * FROM reply WHERE quest_id=#{questId} ORDER BY reg_time DESC" +
            " LIMIT #{limit,jdbcType=INTEGER} OFFSET #{offset,jdbcType=INTEGER}")
    List<ReplyVO> selectByQuestWithPaging(int questId, int limit, int offset);

    @Select("SELECT COUNT(*) FROM reply WHERE quest_id=#{questId}")
    int getTotalNum(int questId);

    @Delete("DELETE FROM reply WHERE id=#{id}")
    int delete(int id);

    @Select("SELECT * FROM reply WHERE id=#{id}")
    ReplyVO selectOne(int id);

    @Update("UPDATE reply SET content=#{content} WHERE id=#{id}")
    int update(ReplyVO vo);
}
