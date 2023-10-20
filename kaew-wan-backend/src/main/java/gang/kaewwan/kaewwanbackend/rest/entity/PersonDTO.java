package gang.kaewwan.kaewwanbackend.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    Long id;
//    PersonUserDTO user;
    String fname;
    String lname;
    String image;
}
