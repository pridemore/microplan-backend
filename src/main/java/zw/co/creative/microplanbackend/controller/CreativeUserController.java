package zw.co.creative.microplanbackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import zw.co.creative.microplanbackend.domain.CreativeUser;
import zw.co.creative.microplanbackend.domain.dto.CreativeUserDto;
import zw.co.creative.microplanbackend.persistance.CreativeUserRepository;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/creativeUser")
@Slf4j
public class CreativeUserController {

    @Autowired
    private AuthenticatedEmployee authenticatedEmployee;

    @Autowired
    private CreativeUserRepository creativeUserRepository;

    private final RestTemplate restTemplate;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerCreativeUser(Model model)
    {
        model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "+authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "pages/access/register";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewCreativeUsers(Model model)
    {
        model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "+authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "pages/access/register";
    }

    @RequestMapping("/create")
    public String createUser(@Validated CreativeUserDto creativeUserDto, Model model) {
        log.info("Dto : {}",creativeUserDto);
        if (Objects.nonNull(creativeUserDto)) {
            String url = "http://localhost:8020/api/creativeUser/create";
            HttpHeaders headers = extractHeaders();
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            final URI resultURL = builder.build().toUri();
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<Object> exchange = restTemplate.postForEntity(resultURL,creativeUserDto,Object.class);
            log.info("Response from creating User------ : {}", exchange);

            List<CreativeUser> creativeUser= creativeUserRepository.findAll();

            model.addAttribute("creativeUsers",creativeUser);
            model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "+authenticatedEmployee.getAuthenticatedUser().getLastName());
            return "pages/profile/view";
        }else{
            model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "+authenticatedEmployee.getAuthenticatedUser().getLastName());
            return "pages/access/register";
        }
    }

    @RequestMapping(value = "/viewAll")
    public String CREATIVEUSER(Model model){
        List<CreativeUser> creativeUser= creativeUserRepository.findAll();

        model.addAttribute("creativeUsers",creativeUser);
        model.addAttribute("space", " ");
        model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "+authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "pages/profile/view";
    }

    private HttpHeaders extractHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Content-Type", "application/json");
        return headers;
    }
}
