package gang.kaewwan.kaewwanbackend.security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    Long id;
    String fname;
    String lname;
    String image;
}
