package gang.kaewwan.kaewwanbackend.security.dao;

import gang.kaewwan.kaewwanbackend.security.entity.User;

public interface UserDao {
    User findByUsername(String username);

    User save(User user);
}
