package zw.co.creative.microplanbackend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import zw.co.creative.microplanbackend.domain.CreativeUser;
import zw.co.creative.microplanbackend.service.impl.CreativeUserServiceImpl;

@Controller
public class AuthenticatedEmployee {
    @Autowired
    private CreativeUserServiceImpl creativeUserService;

    public AuthenticatedEmployee() {
    }

    public CreativeUser getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        CreativeUser employees = new CreativeUser();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        CreativeUser employees1 = creativeUserService.findByEmail(username);
        employees.setEmail(username);
        employees.setFirstName(employees1.getFirstName());
        employees.setLastName(employees1.getLastName());
        return employees;
    }
}
