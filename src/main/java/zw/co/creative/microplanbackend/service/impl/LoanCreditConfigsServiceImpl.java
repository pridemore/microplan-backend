package zw.co.creative.microplanbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.LoanCreditConfigs;
import zw.co.creative.microplanbackend.domain.LoanInterestConfigs;
import zw.co.creative.microplanbackend.domain.dto.LoanCreditConfigsDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.LoanCreditConfigsRepository;
import zw.co.creative.microplanbackend.service.LoanCreditConfigsService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanCreditConfigsServiceImpl implements LoanCreditConfigsService {
    @Autowired
    private LoanCreditConfigsRepository loanCreditConfigsRepository;

    @Override
    public CommonResponse createLoanCreditConfigs(LoanCreditConfigsDto loanCreditConfigsDto) {
        log.info("Loan Credit Config to be saved------: {}", loanCreditConfigsDto);
        LoanCreditConfigs loanCreditConfigs = LoanCreditConfigs.builder()
                .bankName(loanCreditConfigsDto.getBankName())
                .branchName(loanCreditConfigsDto.getBranchName())
                .accountNumber(loanCreditConfigsDto.getAccountNumber())
                .status(CreationStatus.ACTIVE)
                .dateCreated(OffsetDateTime.now())
                .lastUpdated(OffsetDateTime.now())
                .build();
        LoanCreditConfigs saveLoanCreditConfigs = loanCreditConfigsRepository.save(loanCreditConfigs);
        log.info("Saved Loan Credit Configs-------: {}", saveLoanCreditConfigs);
        return new CommonResponse().buildSuccessResponse("Success", saveLoanCreditConfigs);
    }

    @Override
    public CommonResponse updateLoanCreditConfigs(Long id, LoanCreditConfigsDto loanCreditConfigsDto) {
        log.info("Update Loan Credit config Dto-------: {}", loanCreditConfigsDto);
        Optional<LoanCreditConfigs> foundLoanCreditConfigs = loanCreditConfigsRepository.findById(id);
        log.info("found Update Loan Credit config-------: {}", foundLoanCreditConfigs);
        if (!foundLoanCreditConfigs.isPresent()) {
            return new CommonResponse().buildErrorResponse("Loan credit configs not found");
        }

        foundLoanCreditConfigs.get().setBankName(loanCreditConfigsDto.getBankName());
        foundLoanCreditConfigs.get().setBranchName(loanCreditConfigsDto.getBranchName());
        foundLoanCreditConfigs.get().setAccountNumber(loanCreditConfigsDto.getAccountNumber());

        LoanCreditConfigs updatedLoanCreditConfigs = loanCreditConfigsRepository.save(foundLoanCreditConfigs.get());
        log.info("Updated Loan Credit configs-------: {}", updatedLoanCreditConfigs);
        return new CommonResponse().buildSuccessResponse("Success", updatedLoanCreditConfigs);
    }

    @Override
    public CommonResponse getLoanCreditConfigs() {
        List<LoanCreditConfigs> allConfigs = loanCreditConfigsRepository.findAll();
        return new CommonResponse().buildSuccessResponse("Success", allConfigs);
    }

    @Override
    public LoanCreditConfigs findLoanCreditConfigById(Long id) {
        Optional<LoanCreditConfigs> loanCreditConfigById = loanCreditConfigsRepository.findById(id);
        return loanCreditConfigById.get();
    }

    @Override
    public LoanCreditConfigs getFirstLoanCreditConfigs() {
        LoanCreditConfigs firstByStatus = loanCreditConfigsRepository.findFirstByStatus(CreationStatus.ACTIVE);
        return firstByStatus;
    }

    @Override
    public CommonResponse deleteConfigs(Long id) {
        return null;
    }
}
