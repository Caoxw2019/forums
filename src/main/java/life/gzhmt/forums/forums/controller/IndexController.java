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
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false) String search) {

        PaginationDTO hotQuestion=questionService.list(1,200);
        model.addAttribute("hotQuestion",hotQuestion);
        if (search==null||search.equals("")){
            PaginationDTO pagination=questionService.list(page,size);
            model.addAttribute("pagination",pagination);
        }else if (search!=null){
            PaginationDTO pagination=questionService.listSearch(search,page,size);
            model.addAttribute("pagination",pagination);

        }


        return "index";

    }

}
