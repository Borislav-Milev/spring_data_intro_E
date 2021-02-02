package bookshop_system.app.service;

import bookshop_system.app.entity.ex_2.User;
import bookshop_system.app.repository.UserRepository;
import bookshop_system.app.service.contract.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public List<User> findUsersByDomain(String domain) {
        return this.userRepo.findAllByEmailEndingWith(domain);
    }

    @Override
    @Transactional
    public List<User> inactiveUsers(LocalDateTime lastTimeLogged) {
        List<User> users =  this.userRepo.findAllByLastTimeLoggedInBefore(lastTimeLogged);
        return this.userRepo.saveAll(users);
    }
}
