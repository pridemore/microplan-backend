package zw.co.creative.microplanbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.common.response.LoanApplicationResponse;
import zw.co.creative.microplanbackend.domain.DocumentUpload;
import zw.co.creative.microplanbackend.domain.LoanApplication;
import zw.co.creative.microplanbackend.domain.SSBApplication;
import zw.co.creative.microplanbackend.domain.dto.SSBApplicationDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.DocumentsUploadRepository;
import zw.co.creative.microplanbackend.persistance.LoanApplicationRepository;
import zw.co.creative.microplanbackend.persistance.LoanRepository;
import zw.co.creative.microplanbackend.persistance.SSBApplicationRepository;
import zw.co.creative.microplanbackend.service.SSBApplicationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SSBApplicationServiceImpl implements SSBApplicationService {
    private final SSBApplicationRepository ssbApplicationRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final DocumentsUploadRepository documentsUploadRepository;

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
        DocumentUpload documentUpload = toDocumentUploadsObject(params);
        documentsUploadRepository.save(documentUpload);
        return new CommonResponse().buildSuccessResponse("Success", saveLoanApplication);
    }

    @Override
    public CommonResponse findAllLoanApplications(String agent_id) {
        List<LoanApplication> allByStatus = loanApplicationRepository.findByAgentIdAndStatus(Long.valueOf(agent_id),CreationStatus.ACTIVE);
        List<LoanApplicationResponse> loanAppResponseList = new ArrayList<>();
        if (allByStatus.size() > 0) {
            for (LoanApplication loanApplication : allByStatus
            ) {
                LoanApplicationResponse loanApplicationResponse = LoanApplicationResponse.builder()
                        .id(loanApplication.getId())
                        .uniqueRef(loanApplication.getUniqueRef())
                        .application_title(loanApplication.getApplicationTitle())
                        .agent_id(loanApplication.getAgentId())
                        .isSubmitted(loanApplication.getIsSubmitted())
                        .dateAndTime(loanApplication.getDateAndTime())
                        //Loan Details
                        .loanPeriod(loanApplication.getLoanPeriod())
                        .netSalary(loanApplication.getNetSalary())
                        .loanPurpose(loanApplication.getLoanPurpose())
                        .approvedLoanAmount(loanApplication.getApprovedLoanAmount())
                        .loanRepaymentPerMonth(loanApplication.getLoanRepaymentPerMonth())
                        .loanType(loanApplication.getLoanType())
                        .interestRate(loanApplication.getInterestRate())
                        .newLoanAmount(loanApplication.getNewLoanAmount())
                        .price(loanApplication.getPrice())
                        .loanFromDate(loanApplication.getLoanFromDate())
                        .loanToDate(loanApplication.getLoanToDate())

                        //Personal
                        .title(loanApplication.getTitle())
                        .firstName(loanApplication.getFirstName())
                        .lastName(loanApplication.getLastName())
                        .dateOfBirth(loanApplication.getDateOfBirth())
                        .nationalId(loanApplication.getNationalId())
                        .passportNumber(loanApplication.getPassportNumber())
                        .maidenName(loanApplication.getMaidenName())
                        .countryOfBirth(loanApplication.getCountryOfBirth())

                        //Contact Details
                        .residentialAddress(loanApplication.getResidentialAddress())
                        .addressType(loanApplication.getAddressType())
                        .mobileNumber(loanApplication.getMobileNumber())
                        .email(loanApplication.getEmail())
                        //Employment Details
                        .profession(loanApplication.getProfession())
                        .nameOfEmployer(loanApplication.getNameOfEmployer())
                        //.otherNameOfEmployer(loanApplication.getOtherNameOfEmployer())
                        .employerAddress(loanApplication.getEmployerAddress())
                        .positionHeld(loanApplication.getPositionHeld())
                        .employeeNumber(loanApplication.getEmployeeNumber())
                        .expiryOfEmployment(loanApplication.getExpiryOfEmployment())
                        .dateJoined(loanApplication.getDateJoined())
                        .grossSalary(loanApplication.getGrossSalary())
                        .employersEmail(loanApplication.getEmployersEmail())
                        .employersPhoneNumber(loanApplication.getEmployersPhoneNumber())
                        //Bank details
                        .bankName(loanApplication.getBankName())
                        //.otherBankName(loanApplication.getOtherBankName())
                        .branchName(loanApplication.getBranchName())
                        .accountName(loanApplication.getAccountName())
                        .accountNo(loanApplication.getAccountNo())
                        .accountType(loanApplication.getAccountType())
                        //.otherAccountType(loanApplication.getOtherAccountType())
                        .witnessFullName(loanApplication.getWitnessFullName())
                        .witnessFullName2(loanApplication.getWitnessFullName2())
                        .status(loanApplication.getStatus().name())
                        .dateCreated(loanApplication.getDateCreated())
                        .lastUpdated(loanApplication.getLastUpdated())
                        .build();
                loanAppResponseList.add(loanApplicationResponse);
            }
        }
        return new CommonResponse().buildSuccessResponse("Success", loanAppResponseList);

    }

    private LoanApplication toLoanApplicationObject(Map<String, Object> map) {
        String selectedBankName=map.getOrDefault("bankName", "").toString();
        String selectedAccountType=map.getOrDefault("accountType", "").toString();
        String selectedOtherNameOfEmployer=map.getOrDefault("nameOfEmployer","").toString();
        if (map == null) {
            return null;
        }

        if(map.get("otherBankName").toString()!=null||!map.get("otherBankName").toString().equals("")){
            selectedBankName=map.get("otherBankName").toString();
        }

        if(map.get("otherAccountType").toString()!=null||!map.get("otherAccountType").toString().equals("")){
            selectedAccountType=map.get("otherAccountType").toString();
        }

        if(map.get("otherNameOfEmployer").toString()!=null||!map.get("otherNameOfEmployer").toString().equals("")){
            selectedOtherNameOfEmployer=map.get("otherNameOfEmployer").toString();
        }


        LoanApplication loanApplication = LoanApplication.builder()
                .uniqueRef(map.getOrDefault("uniqueRef", "").toString())
                .applicationTitle(map.getOrDefault("application_title", "").toString())
                .agentId(Long.valueOf(map.getOrDefault("agent_id", 0).toString()))
                .isSubmitted("true")
                .dateAndTime(map.getOrDefault("dateAndTime", "").toString())

                //Loan Details
                .loanPeriod(Integer.valueOf(map.getOrDefault("loanPeriod", 0).toString()))
                .netSalary(Double.valueOf(map.getOrDefault("netSalary", 0.0).toString()))
                .loanPurpose(map.getOrDefault("loanPurpose", "").toString())
                .approvedLoanAmount(map.getOrDefault("approvedLoanAmount", "").toString())
                //.establishmentFees(map.getOrDefault("establishmentFees","").toString())
                //.fiftyPercentOfSalary(map.getOrDefault("fiftyPercentOfSalary","").toString())
                //.loanApplicationFee(map.getOrDefault("loanApplicationFee","").toString())
                //.loanInsuranceFees(map.getOrDefault("loanInsuranceFees","").toString())
                .loanRepaymentPerMonth(map.getOrDefault("loanRepaymentPerMonth", "").toString())
                .loanType(map.getOrDefault("loanType", "").toString())
                //.fundsTransferFees(map.getOrDefault("fundsTransferFees","").toString())
                .interestRate(map.getOrDefault("interestRate", "").toString())
                .newLoanAmount(map.getOrDefault("newLoanAmount", "").toString())
                .price(map.getOrDefault("price", "").toString())
                .loanFromDate(map.getOrDefault("loanFromDate", "").toString())
                .loanToDate(map.getOrDefault("loanToDate", "").toString())

                //Personal
                .title(map.getOrDefault("title", "").toString())
                .firstName(map.getOrDefault("firstName", "").toString())
                .lastName(map.getOrDefault("lastName", "").toString())
                .dateOfBirth(map.getOrDefault("dateOfBirth", "").toString())
                .nationalId(map.getOrDefault("nationalId", "").toString())
                .passportNumber(map.getOrDefault("passportNumber", "").toString())
                .maidenName(map.getOrDefault("maidenName", "").toString())
                .countryOfBirth(map.getOrDefault("countryOfBirth", "").toString())

                //Contact Details
                .residentialAddress(map.getOrDefault("residentialAddress", "").toString())
                .addressType(map.getOrDefault("addressType", "").toString())
                .mobileNumber(map.getOrDefault("mobileNumber", "").toString())
                .email(map.getOrDefault("email", "").toString())
                //.currentCitizenship(map.getOrDefault("currentCitizenship","").toString())

                //Employment Details
                .profession(map.getOrDefault("profession", "").toString())
                .nameOfEmployer(selectedOtherNameOfEmployer)
                .employerAddress(map.getOrDefault("employerPhysicalAddress", "").toString())
                .positionHeld(map.getOrDefault("positionHeld", "").toString())
                .employeeNumber(map.getOrDefault("employeeNumber", "").toString())
                .expiryOfEmployment(map.getOrDefault("expiryOfEmployment", "").toString())
                .dateJoined(map.getOrDefault("dateJoined", "").toString())
                .grossSalary(map.getOrDefault("grossSalary", "").toString())
                //.currentNetSalary(map.getOrDefault("currentNetSalary","").toString())
                .employersEmail(map.getOrDefault("employersEmail", "").toString())
                .employersPhoneNumber(map.getOrDefault("employersPhoneNumber", "").toString())
                //.districtAndPayrollCode(map.getOrDefault("districtAndPayrollCode","").toString())

                //Bank details
                .bankName(selectedBankName)
                .branchName(map.getOrDefault("branchName", "").toString())
                .accountName(map.getOrDefault("accountName", "").toString())
                .accountNo(map.getOrDefault("accountNo", "").toString())
                .accountType(selectedAccountType)

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
                .nxtOfKin1FirstName(map.getOrDefault("nxtOfKin1FirstName","").toString())
                .nxtOfKin1Surname(map.getOrDefault("nxtOfKin1Surname","").toString())
                .nxtOfKin1Address(map.getOrDefault("nxtOfKin1ResidentialAddress","").toString())
                .nxtOfKin1Phone(map.getOrDefault("nxtOfKin1PhoneNumber","").toString())
                .nxtOfKin1Relation(map.getOrDefault("nxtOfKin1Relation","").toString())
                //.nxtOfKin1NameOfEmployer(map.getOrDefault("nxtOfKin1NameOfEmployer","").toString())
                //.nxtOfKin1EmployerAddress(map.getOrDefault("nxtOfKin1EmployerAddress","").toString())

                //Next of Kin 2 Details
                //.nxtOfKin2TitleGroup(map.getOrDefault("nxtOfKin2TitleGroup","").toString())
                .nxtOfKin2FirstName(map.getOrDefault("nxtOfKin2FirstName","").toString())
                .nxtOfKin2Surname(map.getOrDefault("nxtOfKin2Surname","").toString())
                .nxtOfKin2Address(map.getOrDefault("nxtOfKin2ResidentialAddress","").toString())
                .nxtOfKin2Phone(map.getOrDefault("nxtOfKin2PhoneNumber","").toString())
                .nxtOfKin2Relation(map.getOrDefault("nxtOfKin2Relation","").toString())
                //.nxtOfKin2NameOfEmployer(map.getOrDefault("nxtOfKin2NameOfEmployer","").toString())
                //.nxtOfKin2EmployerAddress(map.getOrDefault("nxtOfKin2EmployerAddress","").toString())

                //Declaration
                //.borrowerFullName(map.getOrDefault("borrowerFullName","").toString())
                .placeOfSignature(map.getOrDefault("placeOfSignature","").toString())
                //.borrowerSignature(map.getOrDefault("borrowerSignature","").toString())
                //.borrowerSignatureBase64(map.getOrDefault("borrowerSignatureBase64","").toString())
                //.dateSignBorrower(map.getOrDefault("dateSignBorrower","").toString())

                .witnessFullName(map.getOrDefault("witnessFullName","").toString())
                //.witnessSignature(map.getOrDefault("witnessSignature","").toString())
                //.witnessSignatureBase64(map.getOrDefault("witnessSignatureBase64","").toString())
                //.dateSignWitness(map.getOrDefault("dateSignWitness","").toString())
                //.witnessPlaceOfSignature(map.getOrDefault("witnessPlaceOfSignature","").toString())

                .witnessFullName2(map.getOrDefault("witnessFullName2","").toString())
                //.witnessSignature2(map.getOrDefault("witnessSignature2","").toString())
                //.witness2SignatureBase64(map.getOrDefault("witness2SignatureBase64","").toString())
                //.dateSignWitness2(map.getOrDefault("dateSignWitness2","").toString())
                //.witnessPlaceOfSignature2(map.getOrDefault("witnessPlaceOfSignature2","").toString())

                //Office Use Details
                .authorisedBy(map.getOrDefault("authorisedBy", "").toString())
//                .authoriserSignature(map.getOrDefault("authoriserSignature", "").toString())
                //.authorizerSignatureBase64(map.getOrDefault("authorizerSignatureBase64","").toString())
                //.officeStamp(map.getOrDefault("officeStamp","").toString())
                //.officeStampBase64(map.getOrDefault("officeStampBase64","").toString())
                .status(CreationStatus.ACTIVE)
                .dateCreated(OffsetDateTime.now())
                .lastUpdated(OffsetDateTime.now())
                .build();
        return loanApplication;
    }


    private DocumentUpload toDocumentUploadsObject(Map<String, Object> map) {
        if (map == null) {
            return null;
        }

        DocumentUpload documentUpload = DocumentUpload.builder()
                .loanUniqueRef(map.getOrDefault("uniqueRef", "").toString())
                .borrowerSignature(map.getOrDefault("borrowerSignature","").toString())
                .witnessSignature(map.getOrDefault("witnessSignature","").toString())
                .witnessSignature2(map.getOrDefault("witnessSignature2","").toString())

                .nationalIdUpload(map.getOrDefault("nationalIdUpload", "").toString())
                .documentNationalId(map.getOrDefault("documentNationalId", "").toString())
                //.documentNationalIdBase64(map.getOrDefault("documentNationalIdBase64","").toString())

                .clientPictureUpload(map.getOrDefault("clientPictureUpload", "").toString())
                .documentPhoto(map.getOrDefault("documentPhoto", "").toString())
                //.documentPhotoBase64(map.getOrDefault("documentPhotoBase64","").toString())

                .payslipUpload(map.getOrDefault("payslipUpload", "").toString())
                .documentPayslip(map.getOrDefault("documentPayslip", "").toString())
                //.documentPayslipBase64(map.getOrDefault("documentPayslipBase64","").toString())

                .documentInvoice(map.getOrDefault("documentInvoice", "").toString())
                //.documentInvoiceBase64(map.getOrDefault("documentInvoiceBase64","").toString())
                .documentMarriageCertificate(map.getOrDefault("documentMarriageCertificate", "").toString())
                //.documentMarriageCertificateBase64(map.getOrDefault("documentMarriageCertificateBase64","").toString())

                .proofOfEmployemntUpload(map.getOrDefault("proofOfEmployemntUpload", "").toString())
                .documentProofOfEmployment(map.getOrDefault("documentProofOfEmployment", "").toString())
                //.documentProofOfEmploymentBase64(map.getOrDefault("documentProofOfEmploymentBase64","").toString())

                .marriageCertificateUpload(map.getOrDefault("marriageCertificateUpload", "").toString())
                .mariage_cert(map.getOrDefault("mariage_cert", "").toString())

                .serial_num(map.getOrDefault("serial_num", "").toString())
                .serialNumberUpload(map.getOrDefault("serialNumberUpload", "").toString())
                .documentSerialNumber(map.getOrDefault("documentSerialNumber", "").toString())
                //.documentSerialNumberBase64(map.getOrDefault("documentSerialNumberBase64","").toString())

                //.invoiceupload(map.getOrDefault("invoiceupload","").toString())
                .invoicepic(map.getOrDefault("invoicepic", "").toString())
                .build();
        return documentUpload;

    }
}
