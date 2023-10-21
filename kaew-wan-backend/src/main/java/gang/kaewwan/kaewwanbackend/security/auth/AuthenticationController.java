package gang.kaewwan.kaewwanbackend.security.auth;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public AuthenticationResponse register(
      @RequestBody RegisterStudentRequest request) {
    return service.register(request);
  }

  @PostMapping("/add-teacher")
  public AuthenticationResponse addTeacher(
      @RequestBody RegisterTeacherRequest request) {
    return service.addTeacher(request);
  }

  @PostMapping("/authenticate")
  public AuthenticationResponse authenticate(
      @RequestBody AuthenticationRequest request) {
    AuthenticationResponse result = service.authenticate(request);
    return result;
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    service.refreshToken(request, response);
  }

}
