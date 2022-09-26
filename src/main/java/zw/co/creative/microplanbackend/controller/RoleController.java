package zw.co.creative.microplanbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import zw.co.creative.microplanbackend.domain.dto.RoleDto;

import java.net.URI;
import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RestTemplate restTemplate;

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
            log.info("Saved Role response-----: {}",exchange);
            return "pages/roles/view";
        }else{
            return "pages/roles/create";
        }
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewRoles() {
        return "pages/roles/view";
    }

}
