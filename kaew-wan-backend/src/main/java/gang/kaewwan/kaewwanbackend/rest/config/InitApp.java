package gang.kaewwan.kaewwanbackend.rest.config;

import gang.kaewwan.kaewwanbackend.rest.entity.Department;
import gang.kaewwan.kaewwanbackend.rest.entity.Student;
import gang.kaewwan.kaewwanbackend.rest.entity.Teacher;
import gang.kaewwan.kaewwanbackend.rest.repository.DepartmentRepository;
import gang.kaewwan.kaewwanbackend.rest.repository.StudentRepository;
import gang.kaewwan.kaewwanbackend.rest.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final DepartmentRepository departmentRepository;
    final StudentRepository studentRepository;
    final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        List<Department> departments = new ArrayList<>();
        departments.add(Department.builder().name("Department of Computer Science").build());
        departments.add(Department.builder().name("Department of Biology").build());
        departments.add(Department.builder().name("Department of Mathematics").build());
        departments.add(Department.builder().name("Department of History").build());
        departments.add(Department.builder().name("Department of Psychology").build());
        departments.add(Department.builder().name("Department of Chemistry").build());
        departments.add(Department.builder().name("Department of Physics").build());
        departments.add(Department.builder().name("Department of Literature").build());
        departments.add(Department.builder().name("Department of Economics").build());
        departments.add(Department.builder().name("Department of Engineering").build());
        departments.forEach(dep -> departmentRepository.save(dep));

        Teacher teacher = Teacher.builder().position("Manager-san").department(departments.get(0)).fname("Chartchai")
                .lname("Doungsa-ard").image("456").build();
        Student student = Student.builder().studentId("642115003").department(departments.get(0)).fname("Kan")
                .lname("Katpark").image("123").teacher(teacher)
                .build();
        teacherRepository.save(teacher);
        studentRepository.save(student);

    }

}
