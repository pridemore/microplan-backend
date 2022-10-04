package zw.co.creative.microplanbackend.service;

import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.dto.SSBApplicationDto;

public interface SSBApplicationService {

    CommonResponse createSSBApplication(SSBApplicationDto ssbApplicationDto);
}
