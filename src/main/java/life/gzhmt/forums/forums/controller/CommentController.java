package life.gzhmt.forums.forums.controller;

import life.gzhmt.forums.forums.dto.CommentCreateDTO;
import life.gzhmt.forums.forums.dto.ResultDTO;
import life.gzhmt.forums.forums.model.Comment;
import life.gzhmt.forums.forums.model.User;
import life.gzhmt.forums.forums.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;


    @ResponseBody
    @RequestMapping(value = "/comment",method= RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
       User user = (User)request.getSession().getAttribute("user");
       if (user==null){
           return ResultDTO.errorOf(2002,"未登录，不能评论");

       }





        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator((long)user.getId());
        comment.setLikeCount(0);
        //下面这行存在BUG 完成在修
       // commentService.insert(comment);
        return ResultDTO.okOf();


    }
}
