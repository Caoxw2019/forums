package life.gzhmt.forums.forums.mapper;

import life.gzhmt.forums.forums.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,accout_id,token,gmt_create,gmt_modified) values (#{name},#{accoutId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
    @Select("select * from user where token=#{token}")
    User finByToken(String token);
}
