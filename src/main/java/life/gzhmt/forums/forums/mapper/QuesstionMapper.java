package life.gzhmt.forums.forums.mapper;

import life.gzhmt.forums.forums.dto.QuestionDTO;
import life.gzhmt.forums.forums.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuesstionMapper {
    @Insert("insert into Question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId,@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);
    @Select("select count(1) from question where creator=#{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);
    @Select("select * from question where id=#{id}")
    Question getById(@Param(value = "id") Integer id);
    @Update("update question set view_count=view_count+1 where id=#{id}")
    void updateByView(@Param(value = "id") Integer id);
}
