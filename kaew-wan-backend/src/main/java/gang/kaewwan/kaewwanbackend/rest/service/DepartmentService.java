package gang.kaewwan.kaewwanbackend.rest.service;

import gang.kaewwan.kaewwanbackend.rest.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Integer getDepartmentSize();
    Page<Department> getDepartments(Pageable pageable);
    List<Department> getAllDepartments();
    Optional<Department> getDepartmentById(Long id);

}
