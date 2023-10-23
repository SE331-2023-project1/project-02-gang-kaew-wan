package gang.kaewwan.kaewwanbackend.security.service;

import gang.kaewwan.kaewwanbackend.security.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }
}
