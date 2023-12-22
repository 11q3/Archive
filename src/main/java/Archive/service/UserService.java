package Archive.service;

import Archive.model.User;
import Archive.web.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(UserDto registrationDto);

    User findUserByEmail(String email);
}
