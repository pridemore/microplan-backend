package zw.co.creative.microplanbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.SSBApplication;
import zw.co.creative.microplanbackend.domain.dto.SSBApplicationDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.SSBApplicationRepository;
import zw.co.creative.microplanbackend.service.SSBApplicationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SSBApplicationServiceImpl implements SSBApplicationService {
    private final SSBApplicationRepository ssbApplicationRepository;

    @Override
    public CommonResponse createSSBApplication(SSBApplicationDto dto) {

        SSBApplication ssbApplication = SSBApplication.builder()
                .loanPurpose(dto.getLoanPurpose())
                .employeeNumber(dto.getEmployeeNumber())
                .dateOfApplication(dto.getDateOfApplication())
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .surname(dto.getSurname())
                .applicantTitle(dto.getApplicantTitle())
                .nationalId(dto.getNationalId())
                .passportNo(dto.getPassportNo())
                .maidenName(dto.getMaidenName())
                .spouseName(dto.getSpouseName())
                .dateOfBirth(LocalDate.parse(dto.getDateOfBirth()))
                .residentialAddress(dto.getResidentialAddress())
                .applicantResidentialStatus(dto.getApplicantResidentialStatus())
                .nameOfEmployer(dto.getNameOfEmployer())
                .physicalAddressOfEmployer(dto.getPhysicalAddressOfEmployer())
                .cellNumber(dto.getCellNumber())
                .homeNumber(dto.getHomeNumber())
                .faxNumber(dto.getFaxNumber())
                .employedDate(dto.getEmployedDate())
                .emailAddress(dto.getEmailAddress())
                .profession(dto.getProfession())
                .positionHeld(dto.getPositionHeld())
                .monthlySalary(dto.getMonthlySalary())
                .netSalary(dto.getNetSalary())
                .nameOfSpouseEmployer(dto.getNameOfSpouseEmployer())
                .addressOfSpouseEmployer(dto.getAddressOfSpouseEmployer())
                .spousesNetSalary(dto.getSpousesNetSalary())
                .otherSourcesOfIncome(dto.getOtherSourcesOfIncome())
                .otherSourcesOfIncomeFrequency(dto.getOtherSourcesOfIncomeFrequency())
                .amountOfOtherSourcesIncome(dto.getAmountOfOtherSourcesIncome())
                .nextOfKin2phoneNumber(dto.getNextOfKin2phoneNumber())
                .nextOfKin2ResidentialAddress(dto.getNextOfKin2ResidentialAddress())
                .nextOfKin2EmployerAddress(dto.getNextOfKin2EmployerAddress())
                .nextOfKin2Employer(dto.getNextOfKin2Employer())
                .nextOfKin2Relationship(dto.getNextOfKin2Relationship())
                .nextOfKin2Surname(dto.getNextOfKin2Surname())
                .nextOfKin2Name(dto.getNextOfKin2Name())
                .nextOfKin1phoneNumber(dto.getNextOfKin1phoneNumber())
                .nextOfKin1ResidentialAddress(dto.getNextOfKin1ResidentialAddress())
                .nextOfKin1EmployerAddress(dto.getNextOfKin1EmployerAddress())
                .nextOfKin1Employer(dto.getNextOfKin1Employer())
                .nextOfKin1Relationship(dto.getNextOfKin1Relationship())
                .nextOfKin1Surname(dto.getNextOfKin1Surname())
                .nextOfKin1Name(dto.getNextOfKin1Name())
                .creditFacilityAmountRequired(dto.getCreditFacilityAmountRequired())
                .repaymentPeriodRequired(dto.getRepaymentPeriodRequired())
                .shortTermUsdLoan(dto.getShortTermUsdLoan())
                .applicantSignature(dto.getApplicantSignature())
                .witness1Signature(dto.getWitness1Signature())
                .witness2Signature(dto.getWitness2Signature())
                .signedAtLocation(dto.getSignedAtLocation())
                .applicantStatus(dto.getApplicantStatus())
                .refNumber(dto.getRefNumber())
                .payeeCode(dto.getPayeeCode())
                .checkDigital(dto.getCheckDigital())
                .authorizedByName(dto.getAuthorizedByName())
                .authorizedBySignature(dto.getAuthorizedBySignature())
                .authorizedDate(dto.getAuthorizedDate())
                .status(CreationStatus.ACTIVE)
                .dateCreated(OffsetDateTime.now())
                .lastUpdated(OffsetDateTime.now())
                .build();
        SSBApplication savedSSBApplication = ssbApplicationRepository.save(ssbApplication);

        return new CommonResponse().buildSuccessResponse("Success",savedSSBApplication );
    }

    @Override
    public CommonResponse findAllSSBApplication(CreationStatus status) {
        List<SSBApplication> ssbApplicationList = ssbApplicationRepository.findAllByStatus(CreationStatus.ACTIVE);
        return new CommonResponse().buildSuccessResponse("Success",ssbApplicationList);

    }
}
