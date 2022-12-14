package zw.co.creative.microplanbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SSBApplicationDto {
    private String loanPurpose;
    private String employeeNumber;
    private String dateOfApplication;

    //Personal Details
    private String firstName;
    private String middleName;
    private String surname;
    private String applicantTitle;
    private String nationalId;
    private String passportNo;
    private String maidenName;
    private String spouseName;
    private String dateOfBirth;
    private String residentialAddress;
    private String applicantResidentialStatus;

    //Employee Details
    private String nameOfEmployer;
    private String physicalAddressOfEmployer;
    private String cellNumber;
    private String homeNumber;
    private String faxNumber;
    private String employedDate;
    private String emailAddress;
    private String profession;
    private String positionHeld;
    private String monthlySalary;
    private String netSalary;
    private String nameOfSpouseEmployer;
    private String addressOfSpouseEmployer;
    private String spousesNetSalary;
    private String otherSourcesOfIncome;
    private String otherSourcesOfIncomeFrequency;
    private String amountOfOtherSourcesIncome;

    //Next Of Kin Details
    private String nextOfKin2phoneNumber;
    private String nextOfKin2ResidentialAddress;
    private String nextOfKin2EmployerAddress;
    private String nextOfKin2Employer;
    private String nextOfKin2Relationship;
    private String nextOfKin2Surname;
    private String nextOfKin2Name;
    private String nextOfKin1phoneNumber;
    private String nextOfKin1ResidentialAddress;
    private String nextOfKin1EmployerAddress;
    private String nextOfKin1Employer;
    private String nextOfKin1Relationship;
    private String nextOfKin1Surname;
    private String nextOfKin1Name;

    //Loan Details
    private String creditFacilityAmountRequired;
    private String repaymentPeriodRequired;
    private String shortTermUsdLoan;

    //Decleration By Customer
    private String applicantSignature;
    private String witness1Signature;
    private String witness2Signature;
    private String signedAtLocation;

    //Allowance/Deductions
    private String applicantStatus;
    private String refNumber;
    private String payeeCode;
    private String checkDigital;

    //Decleration By Applicant
    private String authorizedByName;
    private String authorizedBySignature;
    private String authorizedDate;
}
