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
public class NextOfKin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String relationship;
    private String residentialAddress;
    private String phoneNumber;
    private String employerName;
    private String EmployerAddress;

    @Enumerated(EnumType.STRING)
    private CreationStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @UpdateTimestamp
    protected OffsetDateTime lastUpdated;

}
