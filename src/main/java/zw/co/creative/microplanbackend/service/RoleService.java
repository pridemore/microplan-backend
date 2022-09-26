package zw.co.creative.microplanbackend.service;

import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.Role;
import zw.co.creative.microplanbackend.domain.dto.RoleDto;

import java.util.List;

public interface RoleService {

    CommonResponse createRole(RoleDto roleDto);
    CommonResponse getRolesByCreationStatus();
}
