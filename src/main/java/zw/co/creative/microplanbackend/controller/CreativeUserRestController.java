package zw.co.creative.microplanbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.dto.CreativeUserDto;
import zw.co.creative.microplanbackend.domain.dto.LoginDto;
import zw.co.creative.microplanbackend.service.CreativeUserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/creativeUser")
public class CreativeUserRestController {

    private final CreativeUserService creativeUserService;


    @PostMapping("/create")
    public CommonResponse createUser(@RequestBody CreativeUserDto createUserDto) {

        CommonResponse creativeUser = creativeUserService.createUser(createUserDto);
        return creativeUser;
    }

    @PostMapping("/login")
    public CommonResponse authenticateUser(@RequestBody LoginDto loginDto) {
        CommonResponse loginResponse = creativeUserService.userLogin(loginDto);
        log.info("Login Response-----: {}", loginResponse);
        return loginResponse;

    }

    @PostMapping("/update/{id}")
    public CommonResponse updateUser(@PathVariable("id") Long id, @RequestBody CreativeUserDto creativeUserDto) {
        CommonResponse updateUserResponse = creativeUserService.updateUser(id, creativeUserDto);
        log.info("Update User Response-----: {}", updateUserResponse);
        return updateUserResponse;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse deleteUser(@PathVariable("id") Long id) {
        CommonResponse deleteUserResponse = creativeUserService.deleteUser(id);
        log.info("Delete User Response-----: {}", deleteUserResponse);
        return deleteUserResponse;
    }
}
