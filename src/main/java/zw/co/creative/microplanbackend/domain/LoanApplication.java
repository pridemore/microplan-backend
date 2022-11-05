package zw.co.creative.microplanbackend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    private String uniqueRef;
    private String applicationTitle;
    private long agentId;
    private String isSubmitted="true";
    private String dateAndTime;

    //Loan Details
    private int loanPeriod;
    private double netSalary;
    private String loanPurpose;
    private String approvedLoanAmount;
    //private String establishmentFees;
    //private String fiftyPercentOfSalary;
    //private String loanApplicationFee;
    //private String loanInsuranceFees;
    private String loanRepaymentPerMonth;
    private String loanType;
    //private String fundsTransferFees;
    private String interestRate;
    private String newLoanAmount;
    private String price;
    private String loanFromDate;
    private String loanToDate;

    //Personal
    private String title;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String nationalId;
    private String passportNumber;
    private String maidenName;
    private String countryOfBirth;

    //Contact Details
    public String residentialAddress;
    private String addressType;
    private String mobileNumber;
    private String email;
    //private String currentCitizenship;

    //Employment Details
    private String profession;
    private String nameOfEmployer;
    //private String otherNameOfEmployer;
    private String employerAddress;
    private String positionHeld;
    private String employeeNumber;
    private String expiryOfEmployment;
    private String dateJoined;
    private String grossSalary;
    //private String currentNetSalary;
    private String employersEmail;
    private String employersPhoneNumber;
    //private String districtAndPayrollCode;

    //Bank details
    private String bankName;
    //private String otherBankName;
    private String branchName;
    private String accountName;
    private String accountNo;
    private String accountType;
    //private String otherAccountType;

    //Next of Kin 1 Details
//    @Column(name = "kin1_title")
//    private String nxtOfKin1Title;
    @Column(name = "kin1_first_name")
    private String nxtOfKin1FirstName;
    @Column(name = "kin1_surname")
    private String nxtOfKin1Surname;
    @Column(name = "kin1_address")
    private String nxtOfKin1Address;
    @Column(name = "kin1_phone")
    private String nxtOfKin1Phone;
    @Column(name = "kin1_relation")
    private String nxtOfKin1Relation;
//    @Column(name = "kin1_employer")
//    private String nxtOfKin1Employer;
//    @Column(name = "kin1_employer_address")
//    private String nxtOfKin1EmployerAddress;

    //Next of Kin 2 Details
//    @Column(name = "kin2_title")
//    private String nxtOfKin2Title;
    @Column(name = "kin2_first_name")
    private String nxtOfKin2FirstName;
    @Column(name = "kin2_surname")
    private String nxtOfKin2Surname;
    @Column(name = "kin2_address")
    private String nxtOfKin2Address;
    @Column(name = "kin2_phone")
    private String nxtOfKin2Phone;
    @Column(name = "kin2_relation")
    private String nxtOfKin2Relation;
//    @Column(name = "kin2_employer")
//    private String nxtOfKin2Employer;
//    @Column(name = "kin2_employer_address")
//    private String nxtOfKin2EmployerAddress;

    //Declaration
//    @Column(name = "borrower_name")
//    private String borrowerFullName;
    @Column(name = "signature_place")
    private String placeOfSignature;
//    @Column(name = "borrower_signature")
//    private String borrowerSignature;
    //    @Column(name = "borrower_signature64")
//    private String borrowerSignatureBase64;
//    @Column(name = "date_sign_borrower")
//    private String dateSignBorrower;
    //
    @Column(name = "witness1_name")
    private String witnessFullName;
//    @Column(name = "witness1_signature")
//    private String witnessSignature;
    //    @Column(name = "witness1_signature64")
//    private String witnessSignatureBase64;
//    @Column(name = "witness1_date_sign")
//    private String dateSignWitness;
//    @Column(name = "witness1_signature_place")
//    private String witnessPlaceOfSignature;
    //
    @Column(name = "witness2_name")
    private String witnessFullName2;
//    @Column(name = "witness2_signature")
//    private String witnessSignature2;
//    @Column(name = "witness2_signature64")
//    private String witness2SignatureBase64;
//    @Column(name = "witness2_date_sign")
//    private String dateSignWitness2;
//    @Column(name = "witness2_signature_place")
//    private String witnessPlaceOfSignature2;

    //Static Details
    private String penaltyRate;
    private String defaultInterest;
    private String creditBankName;
    private String creditBranchName;
    private String creditAccountNumber;

    //Office Use Details
//    @Column(name = "approved_by")
//    private String authorisedBy;
//    @Column(name = "authoriser_signature")
//    private String authoriserSignature;
    //@Column(name = "authoriser_signature64")
    // private String authorizerSignatureBase64;
//    @Column(name = "office_stamp")
//    private String officeStamp;
//    @Column(name = "office_stamp64")
//    private String officeStampBase64;

    @Enumerated(EnumType.STRING)
    private CreationStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @UpdateTimestamp
    protected OffsetDateTime lastUpdated;
}
