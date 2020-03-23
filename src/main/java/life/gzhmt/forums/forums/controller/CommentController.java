package life.gzhmt.forums.forums.controller;

import life.gzhmt.forums.forums.dto.CommentDTO;
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
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
       User user = (User)request.getSession().getAttribute("user");
       if (user==null){
           return ResultDTO.errorOf(2002,"未登录，不能评论");

       }





        Comment comment = new Comment();
        comment.setParentId(1L);
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator((long)user.getId());
        comment.setLikeCount(0);
        commentService.insert(comment);
        //Map<Object,Object> objectObjectMap=new HashMap<>();
        //objectObjectMap.put("message","成功");
        return ResultDTO.okOf();


    }
}
