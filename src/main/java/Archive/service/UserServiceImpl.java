package Archive.service;

import Archive.model.Role;
import Archive.model.User;
import Archive.repository.UsersRepository;
import Archive.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService{

    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                             registrationDto.getLastName(),
                             registrationDto.getEmail(),
                             registrationDto.getPhoneNumber(),
                             registrationDto.getPublications(),
                             Arrays.asList(new Role("ROLE_USER")));

        return usersRepository.save(user);
    }
}
