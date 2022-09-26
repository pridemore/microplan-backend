package zw.co.creative.microplanbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {



    @GetMapping(value = "/dashboard")
    public String home(){
        return "pages/home/dashboard";
    }

}
