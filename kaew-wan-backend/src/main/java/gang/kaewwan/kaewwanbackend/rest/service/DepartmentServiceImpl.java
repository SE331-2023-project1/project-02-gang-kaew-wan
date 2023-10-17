package gang.kaewwan.kaewwanbackend.rest.service;

import gang.kaewwan.kaewwanbackend.rest.dao.DepartmentDao;
import gang.kaewwan.kaewwanbackend.rest.entity.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
    final DepartmentDao departmentDao;
    @Override
    public Integer getDepartmentSize() {
        return departmentDao.getDepartmentSize();
    }

    @Override
    public Page<Department> getDepartments(Pageable pageable) {
        return departmentDao.getDepartments(pageable);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return departmentDao.getDepartmentById(id);
    }
}
