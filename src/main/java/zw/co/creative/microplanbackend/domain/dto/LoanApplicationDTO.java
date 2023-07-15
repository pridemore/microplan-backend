package zw.co.creative.microplanbackend.domain.dto;

import lombok.*;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationDTO {

    private long id;

    private String uniqueRef;

    private String applicationTitle;

    private long agentId;

    private String agentFirstName;

    private String agentLastName;

    private String loanPurpose;

    private String firstName;

    private String lastName;

    private String employeeNumber;

    private CreationStatus status;

    private OffsetDateTime dateCreated;

    private OffsetDateTime lastUpdated;

}





