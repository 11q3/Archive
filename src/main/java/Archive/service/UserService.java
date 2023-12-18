package Archive.service;

import Archive.model.User;
import Archive.web.dto.UserDto;

public interface UserService {
    void saveUser(UserDto registrationDto);

    User findUserByEmail(String email);
}
