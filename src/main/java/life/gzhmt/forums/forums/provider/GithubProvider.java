package life.gzhmt.forums.forums.provider;


import com.alibaba.fastjson.JSON;
import life.gzhmt.forums.forums.dto.AccessTokenDTO;

import life.gzhmt.forums.forums.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
//该类用于OKHTTPS的POST提交
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {


        MediaType mediatype = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediatype, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();

           String[] split = string.split("&");
            String tokenstr = split[0];
            String token=tokenstr.split("=")[1];
            System.out.printf(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (Exception e) {

        }
        return null;
    }
    }
