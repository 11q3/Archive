package Archive.service;

import Archive.model.User;
import Archive.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(UserRegistrationDto registrationDto);

    User findUserByEmail(String email);

    List<UserRegistrationDto> findAllUsers();
}
