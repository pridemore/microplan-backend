package zw.co.creative.microplanbackend.service;

import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.CreativeUser;
import zw.co.creative.microplanbackend.domain.dto.CreativeUserDto;
import zw.co.creative.microplanbackend.domain.dto.LoginDto;

import java.util.List;

public interface CreativeUserService {

    CommonResponse createUser(CreativeUserDto creativeUserDto);

    CommonResponse getAllUsers();

    CommonResponse userLogin(LoginDto loginDto);

    CommonResponse updateUser(Long id, CreativeUserDto creativeUserDto);

    CommonResponse deleteUser(Long id);
}
