package zw.co.creative.microplanbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.dto.CreativeUserDto;
import zw.co.creative.microplanbackend.service.CreativeUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/creativeUser")
public class CreativeUserRestController {

    private final CreativeUserService creativeUserService;

    @PostMapping("/create")
    public CommonResponse createUser(@RequestBody CreativeUserDto createUserDto){

        CommonResponse creativeUser = creativeUserService.createUser(createUserDto);
        return creativeUser;
    }
}
