package life.gzhmt.forums.forums.controller;

import life.gzhmt.forums.forums.dto.PaginationDTO;
import life.gzhmt.forums.forums.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;



    @GetMapping("/")
   /* public String hello(@RequestParam(name = "name") String name, Model model){
      //  model.addAllAttributes("name",name);
        model.addAttribute("name",name);
        return "index";
    }*/
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size) {
        PaginationDTO pagination=questionService.list(page,size);
        PaginationDTO hotQuestion=questionService.list(1,10);
        model.addAttribute("pagination",pagination);
        model.addAttribute("hotQuestion",hotQuestion);
        return "index";

    }

}
