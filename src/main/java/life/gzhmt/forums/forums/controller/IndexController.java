package life.gzhmt.forums.forums.controller;

import life.gzhmt.forums.forums.mapper.UserMapper;
import life.gzhmt.forums.forums.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;



    @GetMapping("/")
   /* public String hello(@RequestParam(name = "name") String name, Model model){
      //  model.addAllAttributes("name",name);
        model.addAttribute("name",name);
        return "index";
    }*/
    public String index(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.finByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);

                    }
                    break;
                }
            }
        }



        return "index";
    }
}
