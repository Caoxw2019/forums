package life.gzhmt.forums.forums.controller;

import life.gzhmt.forums.forums.dto.QuestionDTO;
import life.gzhmt.forums.forums.mapper.QuesstionMapper;
import life.gzhmt.forums.forums.mapper.UserMapper;
import life.gzhmt.forums.forums.model.Question;
import life.gzhmt.forums.forums.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import life.gzhmt.forums.forums.service.QuestionService;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;



    @GetMapping("/")
   /* public String hello(@RequestParam(name = "name") String name, Model model){
      //  model.addAllAttributes("name",name);
        model.addAttribute("name",name);
        return "index";
    }*/
    public String index(HttpServletRequest request,
                        Model model) {

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
        List<QuestionDTO> questionList=questionService.list();
        model.addAttribute("questions",questionList);
        return "index";

    }
}
