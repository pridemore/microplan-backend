package zw.co.creative.microplanbackend.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Configs {
    private String interestRate;
    private String monthlyRate;
    private String principalDebit;
    private String penaltyRate;
    private String defaultInterest;

    @Enumerated(EnumType.STRING)
    private CreationStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @UpdateTimestamp
    protected OffsetDateTime lastUpdated;
}
