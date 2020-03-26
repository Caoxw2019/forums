package life.gzhmt.forums.forums.service;


import life.gzhmt.forums.forums.dto.CommentDTO;
import life.gzhmt.forums.forums.dto.PaginationDTO;
import life.gzhmt.forums.forums.mapper.CommentMapper;
import life.gzhmt.forums.forums.mapper.QuesstionMapper;
import life.gzhmt.forums.forums.mapper.UserMapper;
import life.gzhmt.forums.forums.model.Comment;
import life.gzhmt.forums.forums.model.Question;
import life.gzhmt.forums.forums.model.User;
import life.gzhmt.forums.forums.dto.QuestionDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class QuestionService {
    //用在整合两个表的数据显示前端

    @Autowired
    private QuesstionMapper quesstionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = quesstionMapper.countByUserId(userId);
        paginationDTO.setPagination(totalCount,page,size);


        //size*(page-1)
        Integer offset=size*(page-1);
        List<Question> questions = quesstionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();


        for (Question question:questions){
            User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestion(questionDTOList);




        return paginationDTO;


    }



    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = quesstionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);


        //size*(page-1)
        Integer offset=size*(page-1);
        List<Question> questions = quesstionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();


        for (Question question:questions){
           User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestion(questionDTOList);




        return paginationDTO;
    }


    public QuestionDTO getById(Integer id) {
       Question question = quesstionMapper.getById(id);
       QuestionDTO questionDTO=new QuestionDTO();
       BeanUtils.copyProperties(question,questionDTO);
       User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;




    }

    public void incView(Integer id) {
     quesstionMapper.updateByView(id);
    }

    //搜索返回List数据
    public PaginationDTO listSearch(String search, Integer page, Integer size) {
        search="%"+search+"%";
        System.out.println(search);
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = quesstionMapper.countSearch(search);
        paginationDTO.setPagination(totalCount,page,size);


        //size*(page-1)
        Integer offset=size*(page-1);
        List<Question> questions = quesstionMapper.listSearch(search,offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();


        for (Question question:questions){
            User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestion(questionDTOList);
        return paginationDTO;
    }
}
