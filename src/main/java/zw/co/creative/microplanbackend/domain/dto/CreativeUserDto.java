package zw.co.creative.microplanbackend.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreativeUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String cellNumber;
    private String gender;
    private String date_of_birth;
    private String houseAddress;
    private String password;
    private String role;
}
