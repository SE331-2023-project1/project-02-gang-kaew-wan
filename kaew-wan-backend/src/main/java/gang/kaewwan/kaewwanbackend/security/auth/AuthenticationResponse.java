package gang.kaewwan.kaewwanbackend.security.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import gang.kaewwan.kaewwanbackend.security.entity.User;
import gang.kaewwan.kaewwanbackend.security.entity.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("refresh_token")
  private String refreshToken;
  private UserDto user;
}
