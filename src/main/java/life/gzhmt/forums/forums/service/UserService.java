package life.gzhmt.forums.forums.service;

import life.gzhmt.forums.forums.mapper.UserMapper;
import life.gzhmt.forums.forums.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
            User dbUser=userMapper.findByAccountId(user.getAccountId());
        System.out.println(user);
            if (dbUser==null){
                userMapper.insert(user);
            }
            else{
                dbUser.setGmtModified(System.currentTimeMillis());
                dbUser.setAvatarUrl(user.getAvatarUrl());
                dbUser.setName(user.getName());
                dbUser.setToken(user.getToken());
                userMapper.update(dbUser);
            }


    }
}
