package gang.kaewwan.kaewwanbackend.security.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import gang.kaewwan.kaewwanbackend.rest.entity.Department;
import gang.kaewwan.kaewwanbackend.rest.entity.Student;
import gang.kaewwan.kaewwanbackend.rest.entity.Teacher;
import gang.kaewwan.kaewwanbackend.rest.repository.DepartmentRepository;
import gang.kaewwan.kaewwanbackend.rest.repository.StudentRepository;
import gang.kaewwan.kaewwanbackend.rest.repository.TeacherRepository;
import gang.kaewwan.kaewwanbackend.security.config.JwtService;
import gang.kaewwan.kaewwanbackend.security.entity.Role;
import gang.kaewwan.kaewwanbackend.security.entity.User;
import gang.kaewwan.kaewwanbackend.security.repository.UserRepository;
import gang.kaewwan.kaewwanbackend.security.token.Token;
import gang.kaewwan.kaewwanbackend.security.token.TokenRepository;
import gang.kaewwan.kaewwanbackend.security.token.TokenType;
import gang.kaewwan.kaewwanbackend.security.util.SecurityMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final TeacherRepository teacherRepository;

    public AuthenticationResponse register(RegisterStudentRequest request) {

        Department department = departmentRepository.findById(request.getDepartmentId()).orElse(null);

        Student person = Student.builder()
                .studentId(request.getStudentId())
                .fname(request.getFirstname())
                .lname(request.getLastname())
                .image(request.getImage())
                .department(department)
                .build();
        studentRepository.save(person);

        User user = User.builder()
                .person(person)
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_STUDENT)
                .build();
        var savedUser = repository.save(user);
        person.setUser(savedUser);
        studentRepository.save(person);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(SecurityMapper.INSTANCE.getUserDto(user))
                .build();
    }

    public AuthenticationResponse addTeacher(RegisterTeacherRequest request) {
        Department department = departmentRepository.findById(request.getDepartmentId()).orElse(null);

        Teacher person = Teacher.builder()
                .position(request.getPosition())
                .fname(request.getFirstname())
                .lname(request.getLastname())
                .image(request.getImage())
                .department(department)
                .build();
        teacherRepository.save(person);

        User user = User.builder()
                .person(person)
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_TEACHER)
                .build();
        var savedUser = repository.save(user);
        person.setUser(savedUser);
        teacherRepository.save(person);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(SecurityMapper.INSTANCE.getUserDto(user))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = repository.findByUsername(request.getUsername())
                .orElseThrow();
//        System.out.println(user);

        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(SecurityMapper.INSTANCE.getUserDto(user))
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            User user = this.repository.findByUsername(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                AuthenticationResponse authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}