package gang.kaewwan.kaewwanbackend.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterTeacherRequest {

    private String position;
    private Long departmentId;
    private String image;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
}
