package gang.kaewwan.kaewwanbackend.rest.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import gang.kaewwan.kaewwanbackend.rest.entity.Student;
import gang.kaewwan.kaewwanbackend.rest.entity.Teacher;
import gang.kaewwan.kaewwanbackend.rest.repository.StudentRepository;
import gang.kaewwan.kaewwanbackend.rest.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TeacherDaoImpl implements TeacherDao {
    final TeacherRepository teacherRepository;
    final StudentRepository studentRepository;

    @Override
    public Integer getTeacherSize() {
        return Math.toIntExact(teacherRepository.count());
    }

    @Override
    public Page<Teacher> getTeachers(Integer pageSize, Integer page) {
        return teacherRepository.findAll(pageSize != null && page != null ? PageRequest.of(page - 1, pageSize)
                : PageRequest.of(0, getTeacherSize()));
    }

    @Override
    public Teacher getTeacher(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher assignStudent(Long id, List<Student> students) {
        if (teacherRepository.existsById(id)) {
            Teacher teacher = teacherRepository.findById(id).orElse(null);
            students.forEach( student -> {
                if(studentRepository.existsById(student.getId())) {
                    Student studentData = studentRepository.findById(student.getId()).orElse(null);
                    studentData.setTeacher(teacher);
                    studentRepository.save(studentData);
                    teacher.getStudents().add(student);
                }
            } );
            teacher.setId(id);
            return teacherRepository.save(teacher);
        }
        return null;
    }
}
