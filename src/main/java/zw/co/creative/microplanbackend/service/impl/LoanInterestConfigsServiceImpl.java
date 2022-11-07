package zw.co.creative.microplanbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.LoanInterestConfigs;
import zw.co.creative.microplanbackend.domain.dto.LoanInterestConfigsDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.LoanInterestConfigsRepository;
import zw.co.creative.microplanbackend.service.LoanInterestConfigsService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanInterestConfigsServiceImpl implements LoanInterestConfigsService {

    @Autowired
    private LoanInterestConfigsRepository loanInterestConfigsRepository;
    @Override
    public CommonResponse createLoanInterestConfigs(LoanInterestConfigsDto loanInterestConfigsDto) {
        log.info("Loan Interest Config Dto to be saved--------: {}",loanInterestConfigsDto);

        LoanInterestConfigs loanInterestConfigs=LoanInterestConfigs.builder()
                .interestRate(loanInterestConfigsDto.getInterestRate())
                .defaultInterest(loanInterestConfigsDto.getDefaultInterest())
                .penaltyRate(loanInterestConfigsDto.getPenaltyRate())
                .status(CreationStatus.ACTIVE)
                .dateCreated(OffsetDateTime.now())
                .lastUpdated(OffsetDateTime.now())
                .build();
        LoanInterestConfigs savedLoanInterestConfis = loanInterestConfigsRepository.save(loanInterestConfigs);
        return new CommonResponse().buildSuccessResponse("Success",savedLoanInterestConfis);
    }

    @Override
    public CommonResponse updateLoanInterestConfigs(Long id, LoanInterestConfigsDto loanInterestConfigsDto) {
        log.info("Update Loan Interest Configs Dto--------: {}",loanInterestConfigsDto);
        Optional<LoanInterestConfigs> foundLoanInterestConfigs=loanInterestConfigsRepository.findById(id);
        log.info("found Loan Interest to be updated-----: {}",foundLoanInterestConfigs);
        if(!foundLoanInterestConfigs.isPresent()){
            return new CommonResponse().buildErrorResponse("Loan Interest Configs not found");
        }
        foundLoanInterestConfigs.get().setInterestRate(loanInterestConfigsDto.getInterestRate());
        foundLoanInterestConfigs.get().setDefaultInterest(loanInterestConfigsDto.getDefaultInterest());
        foundLoanInterestConfigs.get().setPenaltyRate(loanInterestConfigsDto.getPenaltyRate());

        LoanInterestConfigs updatedLoanInterestConfigs = loanInterestConfigsRepository.save(foundLoanInterestConfigs.get());
        return new CommonResponse().buildSuccessResponse("Success",updatedLoanInterestConfigs);
    }

    @Override
    public CommonResponse getLoanInterestConfigs() {
        List<LoanInterestConfigs> allConfigs=loanInterestConfigsRepository.findAll();
        log.info("All interest configs-----: {}",allConfigs);
        return new CommonResponse().buildSuccessResponse("Success",allConfigs);
    }

    @Override
    public LoanInterestConfigs getFirstLoanInterestConfigs() {
        LoanInterestConfigs firstByStatus = loanInterestConfigsRepository.findFirstByStatus(CreationStatus.ACTIVE);
        return firstByStatus;
    }

    @Override
    public CommonResponse deleteLoanInterestConfigs(Long id) {
        return null;
    }
}
