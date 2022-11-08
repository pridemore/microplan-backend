package zw.co.creative.microplanbackend.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanInterestConfigsResponse {
    private String interestRate;
    private String penaltyRate;
    private String defaultInterest;
}
