package zw.co.creative.microplanbackend.service;

import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.Loan;
import zw.co.creative.microplanbackend.domain.LoanCreditConfigs;
import zw.co.creative.microplanbackend.domain.dto.LoanCreditConfigsDto;

public interface LoanCreditConfigsService {

    CommonResponse createLoanCreditConfigs(LoanCreditConfigsDto loanCreditConfigsDto);
    CommonResponse updateLoanCreditConfigs(Long id,LoanCreditConfigsDto loanCreditConfigsDto);
    CommonResponse getLoanCreditConfigs();
    LoanCreditConfigs findLoanCreditConfigById(Long id);
    CommonResponse deleteConfigs(Long id);
    LoanCreditConfigs getFirstLoanCreditConfigs();
}
