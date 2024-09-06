package zw.co.creative.microplanbackend.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zw.co.creative.microplanbackend.domain.CreativeUser;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.CreativeUserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreativeUserServiceDetail implements UserDetailsService {

    private final CreativeUserRepository creativeUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CreativeUser> optionalUser = creativeUserRepository.findCreativeUserByEmailAndStatus(username, CreationStatus.ACTIVE);
        String message = String.format("User with username %s not found.", username);
        log.info("User found? {}", optionalUser.isPresent());

        UserDetails userDetails;

        if(!optionalUser.isPresent()){
            CreativeUser userAccount = optionalUser.get();
            log.info("Loaded user account => {}", userAccount);

            userDetails = User.withUsername(userAccount.getEmail())
                    .password(userAccount.getPassword())
                    .authorities(userAccount.getAuthorities()).build();
            log.info("Loaded user UserDetails => {}", userDetails);
            return userDetails;
        }
        else{
            log.info("Username not found");
            throw new UsernameNotFoundException(message);
        }
    }
}
