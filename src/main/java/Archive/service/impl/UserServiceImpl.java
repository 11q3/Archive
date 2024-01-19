package Archive.service.impl;

import Archive.model.ProfilePicture;
import Archive.model.Role;
import Archive.model.User;
import Archive.repository.RoleRepository;
import Archive.repository.UserRepository;
import Archive.service.UserService;
import Archive.web.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository usersRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user.setProfilePicture(userDto.getProfilePicture());


        Role role = roleRepository.findByName("ROLE_USER");

        if (role == null) {
            role = checkRoleExist();
        }

        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String uploadProfilePicture(ProfilePicture profilePicture, Principal principal)  throws IOException {
        User user = userRepository.findByEmail(principal.getName());

        String userId = user.getId().toString();

        String fileName = userId + "." + Objects.requireNonNull(profilePicture.getFile().getContentType()).split("/")[1];
        String targetLocation= Archive.util.Paths.PROFILE_PICTURE.getPath();

        BufferedImage image = ImageIO.read(profilePicture.getFile().getInputStream());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutputStream);

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray())) {
            Files.copy(
                    byteArrayInputStream,
                    Paths.get(
                            targetLocation +
                                    File.separator +
                                    fileName),
                    StandardCopyOption.REPLACE_EXISTING);
        }

        user.setProfilePicture("static/images/profilepictures" + '/' + fileName);
        userRepository.save(user);

        return "redirect:/account";
    }

    private Role checkRoleExist(){
        Role role = new Role();

        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}