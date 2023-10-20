package gang.kaewwan.kaewwanbackend.security.entity;

import gang.kaewwan.kaewwanbackend.rest.entity.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long id;
    String username;
    String email;
    Role role;
//    PersonDTO person;

}
