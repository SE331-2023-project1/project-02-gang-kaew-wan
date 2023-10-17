package gang.kaewwan.kaewwanbackend.rest.entity;

import gang.kaewwan.kaewwanbackend.security.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonUserDTO {
    Long id;
    String username;
    String email;
    Role role;
}
