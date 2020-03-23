package life.gzhmt.forums.forums.service;

import life.gzhmt.forums.forums.mapper.CommentMapper;
import life.gzhmt.forums.forums.mapper.QuesstionMapper;
import life.gzhmt.forums.forums.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuesstionMapper quesstionMapper;
    @Transactional
    public void insert(Comment comment) {
        //暂时实现简答回复 日后实现回复数增加

        if (comment.getParentId()==null||comment.getParentId()==0){
            return;
        }
        quesstionMapper.updateByComment(comment.getParentId().intValue());
        commentMapper.insert(comment);


    }
}
