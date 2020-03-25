package life.gzhmt.forums.forums.mapper;

import life.gzhmt.forums.forums.dto.QuestionDTO;
import life.gzhmt.forums.forums.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuesstionMapper {
    @Insert("insert into Question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
    @Select("select * from question order by GMT_CREATE DESC limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{userId} order by GMT_CREATE DESC limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId,@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);
    @Select("select count(1) from question where creator=#{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);
    @Select("select * from question where id=#{id}")
    Question getById(@Param(value = "id") Integer id);
    @Update("update question set view_count=view_count+1 where id=#{id}")
    void updateByView(@Param(value = "id") Integer id);
    @Update("update question set COMMENT_COUNT=COMMENT_COUNT+1 where id=#{id}")
    void updateByComment(@Param(value = "id") Long id);
    //这条语句用于显示最近回复的问题列表
    @Select("select * from question where creator=#{userId}")
    List<Question> listById(@Param(value = "userId") Integer userId);
}
