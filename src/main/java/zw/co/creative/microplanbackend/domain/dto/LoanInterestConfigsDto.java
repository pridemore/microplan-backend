package zw.co.creative.microplanbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanInterestConfigsDto {
    private String interestRate;
    private String penaltyRate;
    private String defaultInterest;
}
