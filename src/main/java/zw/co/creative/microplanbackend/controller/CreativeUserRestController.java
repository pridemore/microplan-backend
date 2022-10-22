package zw.co.creative.microplanbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.dto.CreativeUserDto;
import zw.co.creative.microplanbackend.domain.dto.LoginDto;
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

    @PostMapping("/login")
    public CommonResponse authenticateUser(@RequestBody LoginDto loginDto){
        CommonResponse loginResponse= creativeUserService.userLogin(loginDto);

        return loginResponse;

    }

    @PostMapping("/update/{id}")
    public CommonResponse updateUser(@PathVariable("id")Long id ,@RequestBody CreativeUserDto creativeUserDto){
        CommonResponse updateUserResponse= creativeUserService.updateUser(id,creativeUserDto);
        return updateUserResponse;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse deleteUser(@PathVariable("id")Long id){
        CommonResponse deleteUserResponse= creativeUserService.deleteUser(id);
        return deleteUserResponse;
    }
}
