package life.gzhmt.forums.forums.mapper;

import life.gzhmt.forums.forums.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface CommentMapper {
    @Insert("insert into Comment(parent_id,type,commentator,gmt_create,gmt_modified,like_count,content) values(#{parentId},#{type},{#commentator},{#gmtCreate},{#gmtModified},{#likeCount},{#content})")
    void insert(Comment comment);
}
