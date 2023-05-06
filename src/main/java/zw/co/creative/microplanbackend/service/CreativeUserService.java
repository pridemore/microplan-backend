package zw.co.creative.microplanbackend.service;

import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.CreativeUser;
import zw.co.creative.microplanbackend.domain.dto.CreativeUserDto;
import zw.co.creative.microplanbackend.domain.dto.LoginDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import java.util.List;

public interface CreativeUserService {

    CommonResponse createUser(CreativeUserDto creativeUserDto);

    CommonResponse getAllUsers();

    CommonResponse userLogin(LoginDto loginDto);

    CommonResponse updateUser(Long id, CreativeUserDto creativeUserDto);

    CommonResponse deleteUser(Long id);

    CommonResponse updateUserResetPasswordToken(String token, String email);

    CreativeUser getByResetPasswordToken(String token);

    CommonResponse resetPassword(CreativeUser creativeUser, String newPassword);

    List<CreativeUser>getAllCreativeUsersByStatusAndRole(CreationStatus status,String role);

    CreativeUser getUserById(Long id);
}
