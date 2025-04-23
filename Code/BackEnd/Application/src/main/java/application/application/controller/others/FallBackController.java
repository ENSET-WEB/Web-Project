package application.application.controller.others;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FallBackController {
    @RequestMapping("/**")
    public String fallBack() {
        return "redirect:/product";
    }
}
