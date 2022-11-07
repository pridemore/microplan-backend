package zw.co.creative.microplanbackend.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.common.response.LoanInterestConfigsResponse;
import zw.co.creative.microplanbackend.domain.LoanInterestConfigs;
import zw.co.creative.microplanbackend.service.LoanInterestConfigsService;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/interestConfigs")
public class LoanInterestConfigsRestController {

    private final LoanInterestConfigsService loanInterestConfigsService;

    @GetMapping("/getAll")
    public CommonResponse getAllInterestConfigs(){
        LoanInterestConfigs firstLoanInterestConfigs = loanInterestConfigsService.getFirstLoanInterestConfigs();
        LoanInterestConfigsResponse loanInterestConfigsResponse=LoanInterestConfigsResponse.builder()
                .interestRate(firstLoanInterestConfigs.getInterestRate())
                .defaultInterest(firstLoanInterestConfigs.getDefaultInterest())
                .penaltyRate(firstLoanInterestConfigs.getPenaltyRate())
                .build();
        log.info("firstLoanInterestConfigs called from client--------: {}",loanInterestConfigsResponse);
        return new CommonResponse().buildSuccessResponse("Success",loanInterestConfigsResponse);
    }


}
