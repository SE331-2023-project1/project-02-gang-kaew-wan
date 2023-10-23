package gang.kaewwan.kaewwanbackend.rest.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import gang.kaewwan.kaewwanbackend.rest.entity.Department;

public interface  DepartmentRepository extends JpaRepository<Department, Long>{
}
