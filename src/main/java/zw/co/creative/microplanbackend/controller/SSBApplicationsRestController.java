package zw.co.creative.microplanbackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.dto.RoleDto;
import zw.co.creative.microplanbackend.domain.dto.SSBApplicationDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.service.SSBApplicationService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/application")
@AllArgsConstructor
@Slf4j
public class SSBApplicationsRestController {

    private final SSBApplicationService ssbApplicationService;

    @PostMapping("/create")
    public CommonResponse createRole(@RequestBody SSBApplicationDto ssbApplicationDto) {

        CommonResponse createdSsbApplication = ssbApplicationService.createSSBApplication(ssbApplicationDto);
        return createdSsbApplication;
    }

    @GetMapping("/getAll")
    public CommonResponse getAllSSBApplications() {
        return ssbApplicationService.findAllSSBApplication(CreationStatus.ACTIVE);

    }

    @PostMapping(value = "createApplication", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public CommonResponse createLoanApplicationFromAndroid(@RequestParam HashMap<String, Object> params) {
        log.info("Request Map----------: {}", params);
        CommonResponse createdLoanApplication = ssbApplicationService.createLoanApplication(params);
        return createdLoanApplication;
    }


    @GetMapping("/getAllLoans/{agent_id}")
    public CommonResponse getAllLoanApplicationsApplications(@PathVariable("agent_id") String agent_id) {
        return ssbApplicationService.findAllLoanApplications(agent_id);

    }

}
