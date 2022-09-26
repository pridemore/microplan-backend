package zw.co.creative.microplanbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.CreativeUser;
import zw.co.creative.microplanbackend.domain.Role;
import zw.co.creative.microplanbackend.domain.dto.CreativeUserDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.CreativeUserRepository;
import zw.co.creative.microplanbackend.persistance.RoleRepository;
import zw.co.creative.microplanbackend.service.CreativeUserService;

import java.time.LocalDate;
import java.time.OffsetDateTime;
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
        Optional<Role> role=roleRepository.findRoleByIdAndStatus(Long.valueOf
                (creativeUserDto.getRole()),CreationStatus.ACTIVE);
        if(!role.isPresent())
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
                .isCredentialsExpired(false)
                .enabled(false)
                .dateCreated(OffsetDateTime.now())
                .lastUpdated(OffsetDateTime.now())
                .build();

        CreativeUser savedUser = creativeUserRepository.save(creativeUser);

        return new CommonResponse().buildSuccessResponse("Success",savedUser);
    }

    @Override
    public CommonResponse getAllUsers() {
        return null;
    }
}
