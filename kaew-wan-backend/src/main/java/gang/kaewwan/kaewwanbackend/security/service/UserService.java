package gang.kaewwan.kaewwanbackend.security.service;

import gang.kaewwan.kaewwanbackend.security.entity.User;
import jakarta.transaction.Transactional;

public interface UserService {
    User save(User user);

    @Transactional
    User findByUsername(String username);
}
