package zw.co.creative.microplanbackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import zw.co.creative.microplanbackend.domain.dto.SSBApplicationDto;

import java.net.URI;
import java.util.Objects;

import static zw.co.creative.microplanbackend.common.SystemConstants.BASE_URL;

@Controller
@AllArgsConstructor
@RequestMapping("/application")
@Slf4j
public class SSBApplicationController {


    @Autowired
    private AuthenticatedEmployee authenticatedEmployee;

    private final RestTemplate restTemplate;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createSSBApplication(Model model) {
        return "pages/applications/create";
    }

//    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public String registerCreativeUser(Model model)
//    {
//        model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "+authenticatedEmployee.getAuthenticatedUser().getLastName());
//
//        return "pages/applications/create";
//    }

    @RequestMapping("/add")
    public String addSSBApplication(SSBApplicationDto ssbApplicationDto, Model model) {
        log.info("SSBApplicationDto--------------: {}", ssbApplicationDto);
        if (Objects.nonNull(ssbApplicationDto)) {
            String url = BASE_URL+"/api/application/create";
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            final URI resultUrl = builder.build().toUri();
            ResponseEntity<Object> exchange = restTemplate.postForEntity(resultUrl, ssbApplicationDto, Object.class);
            log.info("Saved Application response-----: {}", exchange);
            return "pages/applications/view";
        } else {
            return "pages/applications/create";
        }
    }

//    @RequestMapping("/add")
//    public String addRole(@Validated SSBApplicationDto ssbApplicationDto, Model model) {
//        model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName() + " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
//
//        if (Objects.nonNull(ssbApplicationDto)) {
//            String url = "http://localhost:8020/api/role/create";
//            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
//            final URI resultUrl = builder.build().toUri();
//            ResponseEntity<Object> exchange = restTemplate.postForEntity(resultUrl, ssbApplicationDto, Object.class);
//            log.info("Saved Application response-----: {}", exchange);
//            return "pages/applications/view";
//        } else {
//            return "pages/applications/create";
//        }
//    }

}
