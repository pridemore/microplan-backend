package zw.co.creative.microplanbackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
@RequestMapping("/application")
@Slf4j
public class ApplicationController {
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String registerCreativeUser(Model model) {
        return "pages/applications/create";
    }

}
