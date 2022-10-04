package zw.co.creative.microplanbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.dto.RoleDto;
import zw.co.creative.microplanbackend.domain.dto.SSBApplicationDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.service.SSBApplicationService;

@RestController
@RequestMapping("/api/application")
@AllArgsConstructor
public class SSBApplicationsRestController {

    private final SSBApplicationService ssbApplicationService;

    @PostMapping("/create")
    public CommonResponse createRole(@RequestBody SSBApplicationDto ssbApplicationDto) {

        CommonResponse createdSsbApplication = ssbApplicationService.createSSBApplication(ssbApplicationDto);
        return createdSsbApplication;
    }

    @GetMapping("/getAll")
    public CommonResponse getAllSSBApplications(){
        return ssbApplicationService.findAllSSBApplication(CreationStatus.ACTIVE);

    }
}
