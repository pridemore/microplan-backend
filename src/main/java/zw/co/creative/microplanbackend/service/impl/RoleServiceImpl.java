package zw.co.creative.microplanbackend.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.Role;
import zw.co.creative.microplanbackend.domain.dto.RoleDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.RoleRepository;
import zw.co.creative.microplanbackend.service.RoleService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public CommonResponse createRole(RoleDto roleDto) {
        log.info("Role DTO----- : {}",roleDto);
        Optional<Role>foundRole=roleRepository.findRoleByName(roleDto.getName());
        if(foundRole.isPresent()){
            return new CommonResponse().buildErrorResponse("Role Already Exist");
        }
        Role role=Role.builder()
                .name(roleDto.getName())
                .status(CreationStatus.ACTIVE)
                .dateCreated(OffsetDateTime.now())
                .lastUpdated(OffsetDateTime.now())
                .build();

        Role savedRole = roleRepository.save(role);
        return new CommonResponse().buildSuccessResponse("Success",savedRole);
    }

    @Override
    public CommonResponse getRolesByCreationStatus() {
        List<Role> rolesList=roleRepository.findRolesByStatus(CreationStatus.ACTIVE);
        log.info("Roles list----- : {}",rolesList);
        return new CommonResponse().buildSuccessResponse("Success",rolesList);
    }
}
