package life.gzhmt.forums.forums.service;

import life.gzhmt.forums.forums.dto.CommentDTO;
import life.gzhmt.forums.forums.dto.PaginationDTO;
import life.gzhmt.forums.forums.mapper.CommentMapper;
import life.gzhmt.forums.forums.mapper.QuesstionMapper;
import life.gzhmt.forums.forums.mapper.UserMapper;
import life.gzhmt.forums.forums.model.Comment;
import life.gzhmt.forums.forums.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuesstionMapper quesstionMapper;
    @Autowired
    private UserMapper userMapper;
 //   @Transactional
    public void insert(Comment comment) {
        //暂时实现简答回复 日后实现回复数增加

       if (comment.getParentId()==null||comment.getParentId()==0){
            return;
        }
        quesstionMapper.updateByComment(comment.getParentId());
        commentMapper.create(comment);


    }

    public List<CommentDTO> listByQuestionId(Integer id) {
        List<CommentDTO> commentDTOList=new ArrayList<>();

        List<Comment> comments=commentMapper.listSelectByParentid(id);
        if (comments.size()==0){
            return new ArrayList<>();
        }
        for (Comment comment:comments){
            int id1 = comment.getCommentator().intValue();
            User user= userMapper.findById(id1);
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(user);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;




    }
    public PaginationDTO listRelies(Integer userId) {
        PaginationDTO paginationDTO = new PaginationDTO();
        List<Comment> comments = commentMapper.listCommentByUserid(userId);
        List<CommentDTO> commentDTOList=new ArrayList<>();


        for (Comment comment:comments){
            User user= userMapper.findById(comment.getCommentator().intValue());
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(user);
            commentDTOList.add(commentDTO);
        }
        paginationDTO.setComment(commentDTOList);




        return paginationDTO;
    }
}
