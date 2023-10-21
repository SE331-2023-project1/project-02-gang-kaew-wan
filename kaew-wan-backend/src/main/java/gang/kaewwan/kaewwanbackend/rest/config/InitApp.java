package gang.kaewwan.kaewwanbackend.rest.config;

import java.util.ArrayList;
import java.util.List;

import gang.kaewwan.kaewwanbackend.rest.entity.*;
import gang.kaewwan.kaewwanbackend.rest.repository.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import gang.kaewwan.kaewwanbackend.security.entity.Role;
import gang.kaewwan.kaewwanbackend.security.entity.User;
import gang.kaewwan.kaewwanbackend.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final DepartmentRepository departmentRepository;
    final ReactionRepository reactionRepository;
    final StudentRepository studentRepository;
    final TeacherRepository teacherRepository;
    final PersonRepository personRepository;
    final CommentRepository commentRepository;
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        List<Department> departments = new ArrayList<>();
        departments.add(Department.builder().id(1L).name("Department of Computer Science").build());
        departments.add(Department.builder().id(2L).name("Department of Biology").build());
        departments.add(Department.builder().id(3L).name("Department of Mathematics").build());
        departments.add(Department.builder().id(4L).name("Department of History").build());
        departments.add(Department.builder().id(5L).name("Department of Psychology").build());
        departments.add(Department.builder().id(6L).name("Department of Chemistry").build());
        departments.add(Department.builder().id(7L).name("Department of Physics").build());
        departments.add(Department.builder().id(8L).name("Department of Literature").build());
        departments.add(Department.builder().id(9L).name("Department of Economics").build());
        departments.add(Department.builder().id(10L).name("Department of Engineering").build());
        departments.forEach(dep -> departmentRepository.save(dep));

        Person adminP = Person.builder().id(1L)
                .fname("test")
                .lname("test")
                .image("https://media.discordapp.net/attachments/742780230258786357/1154538223763202178/ezgif.com-crop.gif?width=269&height=269")
                .build();
        User adminU = User.builder().id(1L).email("test2@test2")
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_ADMIN)
                .build();
        userRepository.save(adminU);
        adminP.setUser(adminU);
        personRepository.save(adminP);

        Teacher teacherP = Teacher.builder().id(2L).position("Manager-san").department(departments.get(0))
                .fname("Chartchai")
                .lname("Doungsa-ard")
                .image("https://i1.rgstatic.net/ii/profile.image/280166027808778-1443808194990_Q512/Chartchai-Doungsa-Ard.jpg")
                .build();
        User teacherU = User.builder().id(2L).email("test2@test2")
                .username(teacherP.getFname())
                .password(passwordEncoder.encode("test"))
                .role(Role.ROLE_TEACHER)
                .build();
        userRepository.save(teacherU);
        teacherP.setUser(teacherU);
        teacherRepository.save(teacherP);

        Student studentP = Student.builder().id(3L).studentId("642115003").department(departments.get(0))
                .fname("Kan")
                .lname("Katpark")
                .image("https://media.discordapp.net/attachments/950106830892331088/1132700631036080229/image.png?ex=653e7eaa&is=652c09aa&hm=37eb90b218ef2f20cef945aa2c287dfe24d9ab4d2fb2033211bfe622af30caa2&=&width=505&height=673")
                .teacher(teacherP)
                .build();
        User studentU = User.builder().id(3L).email("test@test")
                .username(studentP.getStudentId())
                .password(passwordEncoder.encode("test"))
                .role(Role.ROLE_STUDENT)
                .build();
        userRepository.save(studentU);
        studentP.setUser(studentU);
        studentRepository.save(studentP);

        Comment comment1 = Comment.builder().id(1L).message("Hello").teacher(teacherP).student(studentP)
                .edited(false)
                .build();
        commentRepository.save(comment1);
        Comment comment2 = Comment.builder().id(2L).message("Hello2").parent(comment1).teacher(teacherP)
                .student(studentP)
                .edited(false).build();
        commentRepository.save(comment2);
        Reaction reaction = Reaction.builder().id(1L).emote("👍").reactable(comment2).build();
        reactionRepository.save(reaction);
    }

}
