package life.gzhmt.forums.forums.controller;


import life.gzhmt.forums.forums.dto.AccessTokenDTO;
import life.gzhmt.forums.forums.dto.GithubUser;
import life.gzhmt.forums.forums.mapper.UserMapper;
import life.gzhmt.forums.forums.model.User;
import life.gzhmt.forums.forums.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthoizeController {
    @Autowired
    private GithubProvider githubProvider;

    //@value作用是在配置文件中加载值到赋值给下面
    @Value("${github.client.id}")
    private String clienId;

    @Value("${github.client.secret}")
    private String clienSecret;

    @Value("${github.redirect.url}")
    private String redirectUri;
    @Autowired
    private UserMapper userMapper;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clienId);
        accessTokenDTO.setClient_secret(clienSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccoutId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));

            //登录成功 写入cookie和session
            request.getSession().setAttribute("user",githubUser);
            System.out.println(githubUser.getName());
            return  "redirect:/";

        }else {
            //登录失败
            System.out.println("登录失败");
            return  "redirect:/";

        }
    }
}
