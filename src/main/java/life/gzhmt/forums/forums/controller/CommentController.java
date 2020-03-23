package life.gzhmt.forums.forums.controller;

import life.gzhmt.forums.forums.dto.CommentDTO;
import life.gzhmt.forums.forums.mapper.CommentMapper;
import life.gzhmt.forums.forums.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;


    @ResponseBody
    @RequestMapping(value = "/comment",method= RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParenId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(1);
        commentMapper.insert(comment);
        Map<Object,Object> objectObjectMap=new HashMap<>();
        objectObjectMap.put("message","成功");
        return null;


    }
}
