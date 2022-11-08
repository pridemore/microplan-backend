package zw.co.creative.microplanbackend.service;

import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.LoanInterestConfigs;
import zw.co.creative.microplanbackend.domain.dto.LoanInterestConfigsDto;

public interface LoanInterestConfigsService {

    CommonResponse createLoanInterestConfigs(LoanInterestConfigsDto loanInterestConfigsDto);
    CommonResponse updateLoanInterestConfigs(Long id,LoanInterestConfigsDto loanInterestConfigsDto);
    CommonResponse getLoanInterestConfigs();
    CommonResponse deleteLoanInterestConfigs(Long id);
    LoanInterestConfigs getFirstLoanInterestConfigs();
}
