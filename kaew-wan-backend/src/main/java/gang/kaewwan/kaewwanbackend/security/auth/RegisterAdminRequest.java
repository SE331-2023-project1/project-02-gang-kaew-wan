package gang.kaewwan.kaewwanbackend.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdminRequest {
    private String username;
    private String image;
    private String fname;
    private String lname;
    private String email;
    private String password;
}
