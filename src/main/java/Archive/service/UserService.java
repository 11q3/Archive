package Archive.service;

import Archive.model.User;
import Archive.web.dto.UserRegistrationDto;

import java.util.List;

public interface UserService {
    void saveUser(UserRegistrationDto registrationDto);

    User findUserByEmail(String email);

    List<UserRegistrationDto> findAllUsers();
}
