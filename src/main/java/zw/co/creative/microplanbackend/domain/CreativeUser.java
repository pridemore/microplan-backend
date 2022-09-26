package zw.co.creative.microplanbackend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUser implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    private String firstName;
    private String lastName;
    @Column(
            nullable = false,
            unique = true
    )
    private String email;
    private String refNumber;
    private String cellNumber;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_of_birth;
    private String houseAddress;
    private String ipAddress;
    private String password;
    private String role;
    private String resetPasswordToken;
    private Boolean enabled = false;
    private boolean isAccountLocked;
    private boolean isCredentialsExpired;
    @Enumerated(EnumType.STRING)
    private CreationStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @UpdateTimestamp
    protected OffsetDateTime lastUpdated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
