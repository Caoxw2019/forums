package life.gzhmt.forums.forums.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @GetMapping("/")
   /* public String hello(@RequestParam(name = "name") String name, Model model){
      //  model.addAllAttributes("name",name);
        model.addAttribute("name",name);
        return "index";

    }*/
    public String IndexController() {
        return "index";
    }
}
