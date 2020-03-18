package life.gzhmt.forums.forums.controller;


import life.gzhmt.forums.forums.dto.AccessTokenDTO;
import life.gzhmt.forums.forums.dto.GithubUser;
import life.gzhmt.forums.forums.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clienId);
        accessTokenDTO.setClient_secret(clienSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());

        return "index";
    }
}
