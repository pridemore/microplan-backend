package zw.co.creative.microplanbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.dto.RoleDto;
import zw.co.creative.microplanbackend.service.RoleService;

@RestController
@RequestMapping("/api/role")
@AllArgsConstructor
public class RoleRestController {

    private final RoleService roleService;

    @PostMapping("/create")
    public CommonResponse createRole(@RequestBody RoleDto roleDto) {

        CommonResponse roleList = roleService.createRole(roleDto);
        return roleList;
    }

    @GetMapping("/getRoles")
    public CommonResponse getAllRoles() {
        CommonResponse rolesByStatus = roleService.getRolesByCreationStatus();
        return rolesByStatus;
    }
}
