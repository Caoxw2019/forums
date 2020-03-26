package life.gzhmt.forums.forums.mapper;

import life.gzhmt.forums.forums.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into Comment(PARENT_ID,TYPE,COMMENTATOR,GMT_CREATE,GMT_MODIFIED,LIKE_COUNT,CONTENT) values(#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{likeCount},#{content})")
    void create(Comment comment);
    @Select("select * from comment where parent_id=#{id}")
    List<Comment> listSelectByParentid(@Param(value = "id") Integer id);
    @Select("select * from COMMENT,QUESTION,USER where  USER.ID=#{id} and COMMENT.PARENT_ID=QUESTION.ID and USER.ID=QUESTION.CREATOR order by GMT_CREATE DESC")
    List<Comment> listCommentByUserid(@Param(value = "id") Integer userId);
    @Select("select count(*) from COMMENT,QUESTION,USER where COMMENT.PARENT_ID=QUESTION.ID and COMMENT.TYPE=1 and QUESTION.CREATOR=#{id};")
    Integer countTypeByUserid(@Param(value = "id") Integer userId);
    @Update("update COMMENT set  COMMENT.TYPE=0 where COMMENT.PARENT_ID in (select QUESTION.ID from QUESTION,USER where USER.ID=#{userId}  and QUESTION.ID=#{questionId} and QUESTION.CREATOR=USER.ID)")
    void updateType(@Param(value = "userId")Integer userId, @Param(value = "questionId")Integer questionId);
}
