package zw.co.creative.microplanbackend.common.response;

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
public class LoginResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String refNumber;
    private String ipAddress;
    private String status;
    private String role;
}
