package Archive.service;

import Archive.model.User;
import Archive.web.dto.UserRegistrationDto;

public interface UserService {
    User save(UserRegistrationDto registrationDto);
}
