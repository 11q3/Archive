package Archive.service;

import Archive.model.UsersModel;
import Archive.repository.UsersRepository;
import org.springframework.stereotype.Service;

//

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /* public UsersModel registerUser(String login, String password, String email) {
        if (login == null || password == null || email == null) {
            return null;
        } else {
            if(usersRepository.findFirstByEmail(email).isPresent()) {
                System.out.println("duplicate email error");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);

            return usersRepository.save(usersModel);
        }
    }

    public UsersModel authenticate(String email, String password) {
        return usersRepository.findByEmailAndPassword(email, password).orElse(null);
    }*/
}