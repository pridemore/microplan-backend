package zw.co.creative.microplanbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreativeUserResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String refNumber;
    private String cellNumber;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_of_birth;
    private String houseAddress;
    private String ipAddress;
    private String status;
    private String role;
    private OffsetDateTime dateCreated;
    protected OffsetDateTime lastUpdated;
}
