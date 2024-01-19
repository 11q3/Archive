package Archive.service;

import Archive.model.ProfilePicture;
import Archive.model.User;
import Archive.web.dto.UserDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;

@Service
public interface UserService {
    void saveUser(UserDto registrationDto);

    User findUserByEmail(String email);
    String uploadProfilePicture(ProfilePicture profilePicture, Principal principal)  throws IOException;
}
