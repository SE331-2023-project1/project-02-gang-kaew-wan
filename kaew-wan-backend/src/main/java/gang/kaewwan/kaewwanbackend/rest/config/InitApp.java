package gang.kaewwan.kaewwanbackend.rest.config;

import gang.kaewwan.kaewwanbackend.rest.entity.Department;
import gang.kaewwan.kaewwanbackend.rest.entity.Student;
import gang.kaewwan.kaewwanbackend.rest.repository.DepartmentRepository;
import gang.kaewwan.kaewwanbackend.rest.repository.StudentRepository;
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

        Department dep = Department.builder().id(1L).name("Test").build();
        departmentRepository.save(dep);

        Student student = Student.builder().studentId("642115003").department(dep).fname("Kan").lname("Katpark").image("123")
                .build();

        dep.getStudents().add(student);
        studentRepository.save(student);

    }

}
