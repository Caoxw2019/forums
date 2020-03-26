package life.gzhmt.forums.forums.controller;


import life.gzhmt.forums.forums.dto.CommentCreateDTO;
import life.gzhmt.forums.forums.dto.CommentDTO;
import life.gzhmt.forums.forums.dto.QuestionDTO;
import life.gzhmt.forums.forums.model.User;
import life.gzhmt.forums.forums.service.CommentService;
import life.gzhmt.forums.forums.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model,
                           HttpServletRequest request){

        QuestionDTO questionDTO= questionService.getById(id);
        List<CommentDTO> comments = commentService.listByQuestionId(id);
        //已读功能
        User user = (User)request.getSession().getAttribute("user");
        if(user!=null) {
            if ((int)user.getId()==(int)questionDTO.getCreator()) {
                commentService.updateType(user.getId(), questionDTO.getId());
            }
        }
        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";




    }
}
