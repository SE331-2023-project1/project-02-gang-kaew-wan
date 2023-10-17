package gang.kaewwan.kaewwanbackend.rest.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gang.kaewwan.kaewwanbackend.rest.entity.Student;
import gang.kaewwan.kaewwanbackend.rest.entity.StudentDTO;
import gang.kaewwan.kaewwanbackend.rest.service.StudentService;
import gang.kaewwan.kaewwanbackend.rest.util.RestMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentController {
    final StudentService studentService;

    @GetMapping("students")
    public List<StudentDTO> getEventLists(
            @RequestParam(value = "_limit", required = false) Integer pageSize,
            @RequestParam(value = "_page", required = false) Integer page,
            @RequestParam(value = "keyword", required = false) String keyword) {
        HttpHeaders responseHeader = new HttpHeaders();
        Page<Student> pageOut;
        if (keyword != null) {
            pageOut = studentService.getStudents(pageSize, page, keyword);
        } else {
            pageOut = studentService.getStudents(pageSize, page);
        }
        responseHeader.set("x-total-count", String.valueOf(pageOut.getTotalElements()));
        return RestMapper.INSTANCE.getStudentDto(pageOut.getContent());
    }

    @GetMapping("students/{id}")
    public Student getEvent(@PathVariable("id") Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping("students")
    public Student addStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("students/{id}")
    public Student updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }
}
