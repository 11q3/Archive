package Archive.repository;

import Archive.model.User;
import Archive.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {

}