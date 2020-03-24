package life.gzhmt.forums.forums.mapper;

import life.gzhmt.forums.forums.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment(parent_id,type,commentator,gmt_create,gmt_modified,like_count,content) values(#{parentId},#{type},{#commentator},{#gmtCreate},{#gmtModified},{#likeCount},{#content})")
    void create(Comment comment);
    @Select("select * from comment where parent_id=#{id} order by GMT_CREATE DESC")
    List<Comment> listSelectByParentid(@Param(value = "id") Integer id);

}
