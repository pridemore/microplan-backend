package zw.co.creative.microplanbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.LoanApplication;
import zw.co.creative.microplanbackend.domain.SSBApplication;
import zw.co.creative.microplanbackend.domain.dto.SSBApplicationDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.LoanApplicationRepository;
import zw.co.creative.microplanbackend.persistance.LoanRepository;
import zw.co.creative.microplanbackend.persistance.SSBApplicationRepository;
import zw.co.creative.microplanbackend.service.SSBApplicationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SSBApplicationServiceImpl implements SSBApplicationService {
    private final SSBApplicationRepository ssbApplicationRepository;
    private final LoanApplicationRepository loanApplicationRepository;

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

        return new CommonResponse().buildSuccessResponse("Success", savedSSBApplication);
    }

    @Override
    public CommonResponse findAllSSBApplication(CreationStatus status) {
        List<SSBApplication> ssbApplicationList = ssbApplicationRepository.findAllByStatus(CreationStatus.ACTIVE);
        return new CommonResponse().buildSuccessResponse("Success", ssbApplicationList);

    }

    public SSBApplication findAllSSBApplicatiById(long id) {

        return ssbApplicationRepository.findById(id);
    }

    public List<SSBApplication> findAll() {
        return ssbApplicationRepository.findAll();
    }

    @Override
    public CommonResponse createLoanApplication(Map<String, Object> params) {
        LoanApplication receivedLoanApplicationRequest = toLoanApplicationObject(params);
        log.info("ReceivedLoanApplicationRequest Object----------: {}", receivedLoanApplicationRequest);
        LoanApplication saveLoanApplication = loanApplicationRepository.save(receivedLoanApplicationRequest);
        return new CommonResponse().buildSuccessResponse("Success", saveLoanApplication);
    }

    private LoanApplication toLoanApplicationObject(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        LoanApplication loanApplication = LoanApplication.builder()
                .uniqueRef(map.getOrDefault("uniqueRef","").toString())
                .application_title(map.getOrDefault("application_title","").toString())
                .agent_id(Long.valueOf(map.getOrDefault("agent_id",0).toString()))
                .isSubmitted(map.getOrDefault("isSubmitted","").toString())
                .dateAndTime(map.getOrDefault("dateAndTime","").toString())

                //Loan Details
                .loanPeriod(Integer.valueOf(map.getOrDefault("loanPeriod",0).toString()))
                .netSalary(Double.valueOf(map.getOrDefault("netSalary",0.0).toString()))
                .loanPurpose(map.getOrDefault("loanPurpose","").toString())
                .approvedLoanAmount(map.getOrDefault("approvedLoanAmount","").toString())
                //.establishmentFees(map.getOrDefault("establishmentFees","").toString())
                //.fiftyPercentOfSalary(map.getOrDefault("fiftyPercentOfSalary","").toString())
                //.loanApplicationFee(map.getOrDefault("loanApplicationFee","").toString())
                //.loanInsuranceFees(map.getOrDefault("loanInsuranceFees","").toString())
                .loanRepaymentPerMonth(map.getOrDefault("loanRepaymentPerMonth","").toString())
                .loanType(map.getOrDefault("loanType","").toString())
                //.fundsTransferFees(map.getOrDefault("fundsTransferFees","").toString())
                .interestRate(map.getOrDefault("interestRate","").toString())
                .newLoanAmount(map.getOrDefault("newLoanAmount","").toString())
                .price(map.getOrDefault("price","").toString())
                .loanFromDate(map.getOrDefault("loanFromDate","").toString())
                .loanToDate(map.getOrDefault("loanToDate","").toString())

                //Personal
                .title(map.getOrDefault("title","").toString())
                .firstName(map.getOrDefault("firstName","").toString())
                .lastName(map.getOrDefault("lastName","").toString())
                .dateOfBirth(map.getOrDefault("dateOfBirth","").toString())
                .nationalId(map.getOrDefault("nationalId","").toString())
                .passportNumber(map.getOrDefault("passportNumber","").toString())
                .maidenName(map.getOrDefault("maidenName","").toString())
                .countryOfBirth(map.getOrDefault("countryOfBirth","").toString())

                //Contact Details
                .residentialAddress(map.getOrDefault("residentialAddress","").toString())
                .addressType(map.getOrDefault("addressType","").toString())
                .mobileNumber(map.getOrDefault("mobileNumber","").toString())
                .email(map.getOrDefault("email","").toString())
                //.currentCitizenship(map.getOrDefault("currentCitizenship","").toString())

                //Employment Details
                .profession(map.getOrDefault("profession","").toString())
                .nameOfEmployer(map.getOrDefault("nameOfEmployer","").toString())
                .otherNameOfEmployer(map.getOrDefault("otherNameOfEmployer","").toString())
                .employerAddress(map.getOrDefault("employerPhysicalAddress","").toString())
                .positionHeld(map.getOrDefault("positionHeld","").toString())
                .employeeNumber(map.getOrDefault("employeeNumber","").toString())
                .expiryOfEmployment(map.getOrDefault("expiryOfEmployment","").toString())
                .dateJoined(map.getOrDefault("dateJoined","").toString())
                .grossSalary(map.getOrDefault("grossSalary","").toString())
                //.currentNetSalary(map.getOrDefault("currentNetSalary","").toString())
                .employersEmail(map.getOrDefault("employersEmail","").toString())
                .employersPhoneNumber(map.getOrDefault("employersPhoneNumber","").toString())
                //.districtAndPayrollCode(map.getOrDefault("districtAndPayrollCode","").toString())

                //Bank details
                .bankName(map.getOrDefault("bankName","").toString())
                .otherBankName(map.getOrDefault("otherBankName","").toString())
                .branchName(map.getOrDefault("branchName","").toString())
                .accountName(map.getOrDefault("accountName","").toString())
                .accountNo(map.getOrDefault("accountNo","").toString())
                .accountType(map.getOrDefault("accountType","").toString())
                .otherAccountType(map.getOrDefault("otherAccountType","").toString())

                //Documents
                //.nationalIdUpload(map.getOrDefault("nationalIdUpload","").toString())
                //.documentNationalId(map.getOrDefault("documentNationalId","").toString())
                //.documentNationalIdBase64(map.getOrDefault("documentNationalIdBase64","").toString())

                //.clientPictureUpload(map.getOrDefault("clientPictureUpload","").toString())
                //.documentPhoto(map.getOrDefault("documentPhoto","").toString())
                //.documentPhotoBase64(map.getOrDefault("documentPhotoBase64","").toString())

                //.payslipUpload(map.getOrDefault("payslipUpload","").toString())
                //.documentPayslip(map.getOrDefault("documentPayslip","").toString())
                //.documentPayslipBase64(map.getOrDefault("documentPayslipBase64","").toString())

                //.documentInvoice(map.getOrDefault("documentInvoice","").toString())
                //.documentInvoiceBase64(map.getOrDefault("documentInvoiceBase64","").toString())
                //.documentMarriageCertificate(map.getOrDefault("documentMarriageCertificate","").toString())
                //.documentMarriageCertificateBase64(map.getOrDefault("documentMarriageCertificateBase64","").toString())

                //.proofOfEmployemntUpload(map.getOrDefault("proofOfEmployemntUpload","").toString())
                //.documentProofOfEmployment(map.getOrDefault("documentProofOfEmployment","").toString())
                //.documentProofOfEmploymentBase64(map.getOrDefault("documentProofOfEmploymentBase64","").toString())

                //.marriageCertificateUpload(map.getOrDefault("marriageCertificateUpload","").toString())
                //.mariage_cert(map.getOrDefault("mariage_cert","").toString())

                //.serial_num(map.getOrDefault("serial_num","").toString())
                //.serialNumberUpload(map.getOrDefault("serialNumberUpload","").toString())
                //.documentSerialNumber(map.getOrDefault("documentSerialNumber","").toString())
                //.documentSerialNumberBase64(map.getOrDefault("documentSerialNumberBase64","").toString())

                //.invoiceupload(map.getOrDefault("invoiceupload","").toString())
                //.invoicepic(map.getOrDefault("invoicepic","").toString())

                //Next of Kin 1 Details
                //.nxtOfKin1TitleGroup(map.getOrDefault("nxtOfKin1TitleGroup","").toString())
                //.nxtOfKin1FirstName(map.getOrDefault("nxtOfKin1FirstName","").toString())
                //.nxtOfKin1Surname(map.getOrDefault("nxtOfKin1Surname","").toString())
                //.nxtOfKin1ResidentialAddress(map.getOrDefault("nxtOfKin1ResidentialAddress","").toString())
                //.nxtOfKin1PhoneNumber(map.getOrDefault("nxtOfKin1PhoneNumber","").toString())
                //.nxtOfKin1Relation(map.getOrDefault("nxtOfKin1Relation","").toString())
                //.nxtOfKin1NameOfEmployer(map.getOrDefault("nxtOfKin1NameOfEmployer","").toString())
                //.nxtOfKin1EmployerAddress(map.getOrDefault("nxtOfKin1EmployerAddress","").toString())

                //Next of Kin 2 Details
                //.nxtOfKin2TitleGroup(map.getOrDefault("nxtOfKin2TitleGroup","").toString())
                //.nxtOfKin2FirstName(map.getOrDefault("nxtOfKin2FirstName","").toString())
                //.nxtOfKin2Surname(map.getOrDefault("nxtOfKin2Surname","").toString())
                //.nxtOfKin2ResidentialAddress(map.getOrDefault("nxtOfKin2ResidentialAddress","").toString())
                //.nxtOfKin2PhoneNumber(map.getOrDefault("nxtOfKin2PhoneNumber","").toString())
                //.nxtOfKin2Relation(map.getOrDefault("nxtOfKin2Relation","").toString())
                //.nxtOfKin2NameOfEmployer(map.getOrDefault("nxtOfKin2NameOfEmployer","").toString())
                //.nxtOfKin2EmployerAddress(map.getOrDefault("nxtOfKin2EmployerAddress","").toString())

                //Declaration
                //.borrowerFullName(map.getOrDefault("borrowerFullName","").toString())
                //.placeOfSignature(map.getOrDefault("placeOfSignature","").toString())
                //.borrowerSignature(map.getOrDefault("borrowerSignature","").toString())
                //.borrowerSignatureBase64(map.getOrDefault("borrowerSignatureBase64","").toString())
                //.dateSignBorrower(map.getOrDefault("dateSignBorrower","").toString())

                //.witnessFullName(map.getOrDefault("witnessFullName","").toString())
                //.witnessSignature(map.getOrDefault("witnessSignature","").toString())
                //.witnessSignatureBase64(map.getOrDefault("witnessSignatureBase64","").toString())
                //.dateSignWitness(map.getOrDefault("dateSignWitness","").toString())
                //.witnessPlaceOfSignature(map.getOrDefault("witnessPlaceOfSignature","").toString())

                //.witnessFullName2(map.getOrDefault("witnessFullName2","").toString())
                //.witnessSignature2(map.getOrDefault("witnessSignature2","").toString())
                //.witness2SignatureBase64(map.getOrDefault("witness2SignatureBase64","").toString())
                //.dateSignWitness2(map.getOrDefault("dateSignWitness2","").toString())
                //.witnessPlaceOfSignature2(map.getOrDefault("witnessPlaceOfSignature2","").toString())

                //Office Use Details
                .authorisedBy(map.getOrDefault("authorisedBy","").toString())
                .authoriserSignature(map.getOrDefault("authoriserSignature","").toString())
                //.authorizerSignatureBase64(map.getOrDefault("authorizerSignatureBase64","").toString())
                //.officeStamp(map.getOrDefault("officeStamp","").toString())
                //.officeStampBase64(map.getOrDefault("officeStampBase64","").toString())
                .status(CreationStatus.ACTIVE)
                .dateCreated(OffsetDateTime.now())
                .lastUpdated(OffsetDateTime.now())
                .build();
        return loanApplication;
    }
}
