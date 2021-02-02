package bookshop_system.app.service.contract;

import bookshop_system.app.entity.ex_2.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    List<User> findUsersByDomain(String domain);

    List<User> inactiveUsers(LocalDateTime lastTimeLogged);
}
