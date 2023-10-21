package gang.kaewwan.kaewwanbackend.rest.controller;

import gang.kaewwan.kaewwanbackend.rest.entity.Department;
import gang.kaewwan.kaewwanbackend.rest.entity.DepartmentDTO;
import gang.kaewwan.kaewwanbackend.rest.service.DepartmentService;
import gang.kaewwan.kaewwanbackend.rest.util.RestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController {
    final DepartmentService departmentService;

    @GetMapping("departments")
    public List<DepartmentDTO> getAllDepartment() {
        Page<Department> result = this.departmentService.getDepartments(Pageable.unpaged());
        return RestMapper.INSTANCE.getDepartmentDTO(result.getContent());
    }

    @GetMapping("departments/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable("id") Long id){
        Department result = this.departmentService.getDepartmentById(id).orElse(null);
        return RestMapper.INSTANCE.getDepartmentDTO(result);
    }
}
