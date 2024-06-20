package zw.co.creative.microplanbackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.CreativeUser;
import zw.co.creative.microplanbackend.domain.Role;
import zw.co.creative.microplanbackend.domain.dto.CreativeUserDto;
import zw.co.creative.microplanbackend.domain.dto.CreativeUserResponse;
import zw.co.creative.microplanbackend.persistance.CreativeUserRepository;
import zw.co.creative.microplanbackend.service.CreativeUserService;
import zw.co.creative.microplanbackend.service.RoleService;
import zw.co.creative.microplanbackend.service.impl.RoleServiceImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static zw.co.creative.microplanbackend.common.SystemConstants.BASE_URL;

@Controller
@AllArgsConstructor
@RequestMapping("/creativeUser")
@Slf4j
public class CreativeUserController {

    @Autowired
    private AuthenticatedEmployee authenticatedEmployee;

    @Autowired
    private CreativeUserRepository creativeUserRepository;

    @Autowired
    private CreativeUserService creativeUserService;

    @Autowired
    private RoleServiceImpl  roleService;

    private final RestTemplate restTemplate;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerCreativeUser(Model model) {
        model.addAttribute("roleList", this.roleService.getAllRole());
        model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName() + " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "pages/access/register";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewCreativeUsers(Model model) {
        model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName() + " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "pages/access/register";
    }

    @RequestMapping("/create")
    public String createUser(@Validated CreativeUserDto creativeUserDto, Model model) {
        log.info(" creativeUser Dto -------");
        if (Objects.nonNull(creativeUserDto)) {
            String url = BASE_URL+"/api/creativeUser/create";
            HttpHeaders headers = extractHeaders();
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            final URI resultURL = builder.build().toUri();
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<Object> exchange = restTemplate.postForEntity(resultURL, creativeUserDto, Object.class);
            log.info("Response from creating User------");

            List<CreativeUser> creativeUser = creativeUserRepository.findAll();

            model.addAttribute("creativeUsers", creativeUser);
            model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName() + " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
            return "pages/profile/view";
        } else {
            model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName() + " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
            return "pages/access/register";
        }
    }

    @RequestMapping(value = "/viewAll")
    public String CREATIVEUSER(Model model) {
        CommonResponse allUsers = creativeUserService.getAllUsers();
        List<CreativeUserResponse> creativeUser = (ArrayList<CreativeUserResponse>)allUsers.getResult();

        model.addAttribute("creativeUsers", creativeUser);
        model.addAttribute("space", " ");
        model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName() + " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "pages/profile/view";
    }

    private HttpHeaders extractHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Content-Type", "application/json");
        return headers;
    }

    @GetMapping("/view/{id}")
    public String showCreatedUserPage(Model model, @PathVariable("id") Long id) {

        final Optional<CreativeUser> optionalCreativeUser = creativeUserRepository.findById(id);

        if (optionalCreativeUser.isPresent()) {
            //Attributes required for every page inheriting from fragments template
//            model.addAttribute("title", "Oxygen Hub | Cylinder Sizes");

            //Other attributes
            model.addAttribute("creativeUser", optionalCreativeUser.get());
            model.addAttribute("roleList", this.roleService.getAllRole());

            return "pages/profile/edit";
        } else {
            return "pages/profile/view";
        }
    }

    @PostMapping("/edit/{id}")
    public String editCreatedUserPage(Model model, @PathVariable("id") Long id, @Validated CreativeUserDto creativeUserDto, Errors errors) {
        log.info("Update Creativeser Dto-----------: {}", creativeUserDto);
        if(errors.hasErrors()){
            return "redirect:creativeUser/view/"+id;
        }
        CommonResponse updateUser = creativeUserService.updateUser(id, creativeUserDto);
        log.info("Update user response-----");
        return "redirect:/creativeUser/view/"+id;
    }

    @GetMapping("/delete/{id}")
    public String deleteCreatedUserPage(Model model, @PathVariable("id") Long id) {
        log.info("Delete Creativeser id-----------: {}",id);

        creativeUserService.deleteUser(id);
        return "redirect:/creativeUser/viewAll";
    }

}
