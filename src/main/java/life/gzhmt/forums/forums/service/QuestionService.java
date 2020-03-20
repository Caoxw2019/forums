package life.gzhmt.forums.forums.service;


import life.gzhmt.forums.forums.mapper.QuesstionMapper;
import life.gzhmt.forums.forums.mapper.UserMapper;
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



    public List<QuestionDTO> list() {
        List<Question> questions = quesstionMapper.list();
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question:questions){
           User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}