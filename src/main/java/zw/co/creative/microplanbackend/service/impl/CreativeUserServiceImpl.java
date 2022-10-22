package zw.co.creative.microplanbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.CreativeUser;
import zw.co.creative.microplanbackend.domain.Role;
import zw.co.creative.microplanbackend.domain.dto.CreativeUserDto;
import zw.co.creative.microplanbackend.domain.dto.CreativeUserResponse;
import zw.co.creative.microplanbackend.domain.dto.LoginDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.CreativeUserRepository;
import zw.co.creative.microplanbackend.persistance.RoleRepository;
import zw.co.creative.microplanbackend.service.CreativeUserService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreativeUserServiceImpl implements CreativeUserService {
    private final CreativeUserRepository creativeUserRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public CommonResponse createUser(CreativeUserDto creativeUserDto) {
        log.info("CreativeUserDto--------- : {}", creativeUserDto);
        Optional<Role> role = roleRepository.findRoleByIdAndStatus(Long.valueOf
                (creativeUserDto.getRole()), CreationStatus.ACTIVE.name());
        if (!role.isPresent())
            return new CommonResponse().buildErrorResponse("No such role found");

        CreativeUser creativeUser = CreativeUser.builder()

                .firstName(creativeUserDto.getFirstName())
                .lastName(creativeUserDto.getLastName())
                .email(creativeUserDto.getEmail())
                .refNumber("'C20228867'")
                .cellNumber(creativeUserDto.getCellNumber())
                .gender(creativeUserDto.getGender())
                .date_of_birth(LocalDate.parse(creativeUserDto.getDate_of_birth()))
                .houseAddress(creativeUserDto.getHouseAddress())
                .ipAddress("127.0.0.1")
                .password(bCryptPasswordEncoder.encode(creativeUserDto.getPassword()))
                .role(role.get().getName())
                .status(CreationStatus.ACTIVE)
                .isAccountLocked(false)
                .isEnabled(true)
                .isCredentialsExpired(true)
                .dateCreated(OffsetDateTime.now())
                .lastUpdated(OffsetDateTime.now())
                .build();

        CreativeUser savedUser = creativeUserRepository.save(creativeUser);

        return new CommonResponse().buildSuccessResponse("Success", savedUser);
    }

    public CreativeUser findByEmail(String email) {
        return creativeUserRepository.findCreativeUserByEmail(email);
    }

    @Override
    public CommonResponse getAllUsers() {
        List<CreativeUser> allUsers = creativeUserRepository.findAll();
        List<CreativeUserResponse> listResponse=new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (CreativeUser user:allUsers
             ) {
            CreativeUserResponse response = CreativeUserResponse.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .refNumber(user.getRefNumber())
                    .cellNumber(user.getCellNumber())
                    .gender(user.getGender())
                    .date_of_birth(user.getDate_of_birth())
                    .houseAddress(user.getHouseAddress())
                    .ipAddress(user.getIpAddress())
                    .role(user.getRole())
                    .status(user.getStatus().name())
                    .dateCreated(user.getDateCreated())
                    .lastUpdated(user.getLastUpdated())
                    .build();
            listResponse.add(response);
        }
        return new CommonResponse().buildSuccessResponse("Success",listResponse);
    }

    @Override
    public CommonResponse userLogin(LoginDto loginDto) {

        Optional<CreativeUser> user;
        user = Optional.ofNullable(creativeUserRepository.findCreativeUserByEmail(loginDto.getEmail()));

        if (user.isPresent()) {
            //check if login attempts greater or equal to 3
            if (user.get().isAccountLocked()) {
                return new CommonResponse().buildErrorResponse("Login failed, Contact Admin");
            }

            if (!user.get().getRole().equalsIgnoreCase("Agent"))
                return new CommonResponse().buildErrorResponse("Permission Denied.Contact Admin");

            final String storedPassword = user.get().getPassword();
            CreativeUserResponse response = CreativeUserResponse.builder()
                    .id(user.get().getId())
                    .firstName(user.get().getFirstName())
                    .lastName(user.get().getLastName())
                    .email(user.get().getEmail())
                    .refNumber(user.get().getRefNumber())
                    .cellNumber(user.get().getCellNumber())
                    .gender(user.get().getGender())
                    .date_of_birth(user.get().getDate_of_birth())
                    .houseAddress(user.get().getHouseAddress())
                    .ipAddress(user.get().getIpAddress())
                    .role(user.get().getRole())
                    .dateCreated(user.get().getDateCreated())
                    .lastUpdated(user.get().getLastUpdated())
                    .build();
            if (bCryptPasswordEncoder.matches(loginDto.getPassword(), storedPassword)) {


                return new CommonResponse().buildSuccessResponse("Successful", response);
            } else {

                return new CommonResponse().buildErrorResponse("Invalid Login Credentials. Please try again.");
            }
        } else {
            return new CommonResponse().buildErrorResponse("Invalid Login Credentials. Please try again.");
        }
    }

    @Override
    public CommonResponse updateUser(Long id, CreativeUserDto creativeUserDto) {
        Optional<CreativeUser> foundUser = creativeUserRepository.findByIdAndStatus(id, CreationStatus.ACTIVE);
        if (!foundUser.isPresent()) {
            return new CommonResponse().buildErrorResponse("User not found.");
        }
        Optional<Role> role = roleRepository.findRoleByIdAndStatus(Long.valueOf
                (creativeUserDto.getRole()), CreationStatus.ACTIVE.name());
        if (!role.isPresent()) {
            return new CommonResponse().buildErrorResponse("No such role found");
        }
        foundUser.get().setFirstName(creativeUserDto.getFirstName());
        foundUser.get().setLastName(creativeUserDto.getLastName());
        foundUser.get().setEmail(creativeUserDto.getEmail());
        foundUser.get().setCellNumber(creativeUserDto.getCellNumber());
        foundUser.get().setGender(creativeUserDto.getGender());
        foundUser.get().setDate_of_birth(LocalDate.parse(creativeUserDto.getDate_of_birth()));
        foundUser.get().setHouseAddress(creativeUserDto.getHouseAddress());
        foundUser.get().setRole(role.get().getName());
        CreativeUser updatedUser = creativeUserRepository.save(foundUser.get());
        return new CommonResponse().buildSuccessResponse("Success",updatedUser);
    }

    @Override
    public CommonResponse deleteUser(Long id) {
        Optional<CreativeUser> foundUser = creativeUserRepository.findByIdAndStatus(id, CreationStatus.ACTIVE);
        if (!foundUser.isPresent()) {
            return new CommonResponse().buildErrorResponse("User not found.");
        }

        foundUser.get().setStatus(CreationStatus.DELETED);
        CreativeUser deletedUser = creativeUserRepository.save(foundUser.get());
        return new CommonResponse().buildSuccessResponse("User deleted Successfully.",deletedUser);

    }
}
