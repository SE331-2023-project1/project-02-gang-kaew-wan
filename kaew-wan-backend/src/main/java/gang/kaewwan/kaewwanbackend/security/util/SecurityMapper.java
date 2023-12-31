package gang.kaewwan.kaewwanbackend.security.util;

import gang.kaewwan.kaewwanbackend.security.entity.User;
import gang.kaewwan.kaewwanbackend.security.entity.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SecurityMapper {
    SecurityMapper INSTANCE = Mappers.getMapper(SecurityMapper.class);

    @Mapping(target="role", source = "user.role")
    public UserDTO getUserDto(User user);
}
