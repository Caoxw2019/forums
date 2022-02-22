package life.gzhmt.forums.forums.controller;


import life.gzhmt.forums.forums.dto.AccessTokenDTO;
import life.gzhmt.forums.forums.dto.GithubUser;
import life.gzhmt.forums.forums.mapper.UserMapper;
import life.gzhmt.forums.forums.model.User;
import life.gzhmt.forums.forums.provider.GithubProvider;
import life.gzhmt.forums.forums.service.UserService;
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
    private UserService userService;


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
        System.out.println(accessToken);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.getId());
        if (githubUser!=null&&githubUser.getId()!=null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            userService.createOrUpdate(user);
            //登录成功 写入cookie和session
            System.out.println("登录成功2");
            response.addCookie(new Cookie("token",token));
            request.getSession().setAttribute("user",githubUser);
            return  "redirect:/";
        }else {
            //登录失败
            System.out.println("登录失败");
            return  "redirect:/";

        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";




    }

}
