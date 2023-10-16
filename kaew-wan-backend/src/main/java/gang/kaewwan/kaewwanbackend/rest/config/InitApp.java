package gang.kaewwan.kaewwanbackend.rest.config;

import gang.kaewwan.kaewwanbackend.rest.entity.Department;
import gang.kaewwan.kaewwanbackend.rest.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        Department dep = Department.builder().id(1L).name("Test").build();
        departmentRepository.save(dep);


    }

}
