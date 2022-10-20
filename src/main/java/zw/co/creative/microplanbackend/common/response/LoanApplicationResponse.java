package zw.co.creative.microplanbackend.common.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationResponse {
    private long id;
    private String uniqueRef;
    private String application_title;
    private long agent_id = 7;
    private String isSubmitted;
    private String dateAndTime;
    //Loan Details
    private int loanPeriod;
    private double netSalary;
    private String loanPurpose;
    private String approvedLoanAmount;
    private String loanRepaymentPerMonth;
    private String loanType;
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
    //Employment Details
    private String profession;
    private String nameOfEmployer;
    private String otherNameOfEmployer;
    private String employerAddress;
    private String positionHeld;
    private String employeeNumber;
    private String expiryOfEmployment;
    private String dateJoined;
    private String grossSalary;
    private String employersEmail;
    private String employersPhoneNumber;
    //Bank details
    private String bankName;
    private String otherBankName;
    private String branchName;
    private String accountName;
    private String accountNo;
    private String accountType;
    private String otherAccountType;
    private String witnessFullName;
    private String witnessFullName2;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime dateCreated;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    protected OffsetDateTime lastUpdated;
}
