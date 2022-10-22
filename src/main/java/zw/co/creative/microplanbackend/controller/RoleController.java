package zw.co.creative.microplanbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import zw.co.creative.microplanbackend.domain.Role;
import zw.co.creative.microplanbackend.domain.dto.RoleDto;
import zw.co.creative.microplanbackend.service.impl.RoleServiceImpl;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping(
        value = {"/roles"},
        produces = {"application/json"})

//@RequiredArgsConstructor

public class RoleController {

    //private final RestTemplate restTemplate;

    @Autowired
    private RoleServiceImpl roleService;

    /*
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createRole() {
        return "pages/roles/create";
    }
@RequestMapping("/add")
    public String addRole(@Validated RoleDto roleDto, Model model) {

        if (Objects.nonNull(roleDto)) {
            String url = "http://localhost:8020/api/role/create";
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            final URI resultUrl = builder.build().toUri();
            ResponseEntity<Object> exchange = restTemplate.postForEntity(resultUrl, roleDto, Object.class);
            log.info("Saved Role response-----: {}", exchange);
            return "pages/roles/view";
        } else {
            return "pages/roles/create";
        }
    }*/


    @Autowired
    private AuthenticatedEmployee authenticatedEmployee;


    @RequestMapping({"/add"})
    public String addEmployee(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "+authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "/pages/roles/create";
    }

    @RequestMapping({"/create"})
    public String create(@ModelAttribute("employees") @Validated Role role, Model model) {
        Optional<Role> role1 = this.roleService.findRoleByName(role.getName());

        if (role1.isPresent()) {
            model.addAttribute("message", "Role Already exist");
            model.addAttribute("role",new Role());
            model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "+authenticatedEmployee.getAuthenticatedUser().getLastName());

            return "/pages/roles/create";
        } else {
            this.roleService.createRoles(role);

            return "redirect:/roles/view";
        }
    }

    @RequestMapping({"/edit"})
    public String editEmployee(Model model, @RequestParam(value = "id",required = false) String name) {
        Optional<Role> role = this.roleService.findRoleByName(name);
        model.addAttribute("role", this.roleService.findRoleById(((Role)role.get()).getId()));
        model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "+authenticatedEmployee.getAuthenticatedUser().getLastName());

        return "/pages/roles/edit";
    }

    @RequestMapping({"/update"})
    public String updateEmployee(@ModelAttribute("role") @Validated Role role, @RequestParam(value = "id",required = false) long id) {
        this.roleService.updateRole(id, role);
        return "redirect:/roles/view";
    }

    @RequestMapping({"/view"})
    public String findAll(Model model) {
        model.addAttribute("roleList", this.roleService.getAllRole());
        model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "+authenticatedEmployee.getAuthenticatedUser().getLastName());

        return "/pages/roles/view";
    }


   /* @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewRoles() {
        return "pages/roles/view";
    }
*/
}
