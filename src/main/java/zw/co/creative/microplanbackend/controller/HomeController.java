package zw.co.creative.microplanbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zw.co.creative.microplanbackend.domain.SSBApplication;
import zw.co.creative.microplanbackend.service.impl.SSBApplicationServiceImpl;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private SSBApplicationServiceImpl ssbApplicationService;


    private SSBApplication ssbApplication;

    @GetMapping(value = "/dashboard")
    public String home(){
        return "pages/home/dashboard";
    }


    @GetMapping(value = "/viewApplication")
    public String APPLICATION(Model model){
        List<SSBApplication> applications= ssbApplicationService.findAll();

        model.addAttribute("applications",applications);
        model.addAttribute("space", " ");
        return "pages/applications/view";
    }

    @GetMapping(value = "/viewApplicationForm")
    public String viewAPPLICATION(@RequestParam(value = "id",required = false) long id, Model model){
        SSBApplication ssbApplication=ssbApplicationService.findAllSSBApplicatiById(id);
        model.addAttribute("SSBApplication", ssbApplication);

        return "pages/applications/applicationForm";
    }
}



