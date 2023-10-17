package gang.kaewwan.kaewwanbackend.security.auth;

import gang.kaewwan.kaewwanbackend.rest.entity.Department;
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
  private String firstname;
  private String lastname;
  private String username;
  private String email;
  private String password;
}
