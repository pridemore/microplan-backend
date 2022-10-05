package zw.co.creative.microplanbackend.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.Role;
import zw.co.creative.microplanbackend.domain.dto.RoleDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.RoleRepository;
import zw.co.creative.microplanbackend.service.RoleService;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@AllArgsConstructor
@Slf4j
public class RoleServiceImpl {

    /*private final RoleRepository roleRepository;
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
    }*/
    @Autowired
    private RoleRepository roleRepository;


    public Role createRoles(Role roleRequest) {
        Role role = new Role();
        role.setName(roleRequest.getName());
        role.setStatus(roleRequest.getStatus());
        Role savedRole = (Role)this.roleRepository.save(role);
        return savedRole;
    }

    public Role updateRole(long id, Role updateRequest) {
        Role updateRole = this.roleRepository.findById(id);
        updateRole.setStatus(updateRequest.getStatus());
        updateRole.setName(updateRequest.getName());
        Role updatedRole = (Role)this.roleRepository.save(updateRole);
        return updatedRole;
    }

    public Role findRoleById(long id) {
        Role role = this.roleRepository.findById(id);
        return role;
    }

    public List<Role> getAllRole() {
        List<Role> list = new ArrayList();
        this.roleRepository.findAll().forEach(list::add);
        return list;
    }

    public List getList(String status) {
        return this.roleRepository.findAllByStatus(status);
    }

    public Optional<Role> findRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public String deleteRole(long id) {
        Role findRole = this.roleRepository.findById(id);
        this.roleRepository.delete(findRole);
        return "Successfully Deleted";
    }




}
