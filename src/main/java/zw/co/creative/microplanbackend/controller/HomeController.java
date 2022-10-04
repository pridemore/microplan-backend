package zw.co.creative.microplanbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {



    @GetMapping(value = "/dashboard")
    public String home(){
        return "pages/home/dashboard";
    }


    @GetMapping(value = "/viewApplication")
    public String APPLICATION(){
        return "pages/applications/view";
    }

    @GetMapping(value = "/viewApplicationForm")
    public String viewAPPLICATION(){
        return "pages/applications/applicationForm";
    }
}
