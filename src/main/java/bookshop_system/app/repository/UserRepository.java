package bookshop_system.app.repository;

import bookshop_system.app.entity.ex_2.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    //@Query("select u from User u where substring(u.email, locate(u.email, '@')) like ?1")
    List<User> findAllByEmailEndingWith(String provider);

    List<User> findAllByLastTimeLoggedInBefore(LocalDateTime lastTimeLogged);
}
