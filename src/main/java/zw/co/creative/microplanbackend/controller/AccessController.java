package zw.co.creative.microplanbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessController {

    @GetMapping(value = "/login")
    public String login(){
        return "pages/access/login";
    }

    @GetMapping({"/loginerror"})
    public String loginError(Model model) {
        model.addAttribute("message", "Invalid Credentials. Either password or email is incorrect.");
        return "/login";
    }
}
