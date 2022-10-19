package zw.co.creative.microplanbackend.service;

import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.dto.SSBApplicationDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import java.util.Map;

public interface SSBApplicationService {

    CommonResponse createSSBApplication(SSBApplicationDto ssbApplicationDto);
    CommonResponse findAllSSBApplication(CreationStatus status);
    CommonResponse createLoanApplication(Map<String,Object> params);

}
