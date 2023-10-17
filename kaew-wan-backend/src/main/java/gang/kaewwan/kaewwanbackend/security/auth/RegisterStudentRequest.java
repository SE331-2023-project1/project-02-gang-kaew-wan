package gang.kaewwan.kaewwanbackend.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterStudentRequest {

  private String studentId;
  private Long departmentId;
  private String image;
  private String fname;
  private String lname;
  private String username;
  private String email;
  private String password;
}
