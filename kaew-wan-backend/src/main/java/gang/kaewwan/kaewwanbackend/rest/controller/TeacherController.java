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
import gang.kaewwan.kaewwanbackend.rest.entity.StudentListRequest;
import gang.kaewwan.kaewwanbackend.rest.entity.Teacher;
import gang.kaewwan.kaewwanbackend.rest.entity.TeacherDTO;
import gang.kaewwan.kaewwanbackend.rest.service.TeacherService;
import gang.kaewwan.kaewwanbackend.rest.util.RestMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TeacherController {
    final TeacherService teacherService;

    @GetMapping("teachers")
    public ResponseEntity<List<TeacherDTO>> getTeacherList(
            @RequestParam(value = "_limit", required = false) Integer pageSize,
            @RequestParam(value = "_page", required = false) Integer page) {
        HttpHeaders responseHeader = new HttpHeaders();
        Page<Teacher> pageOut;
        pageOut = teacherService.getTeachers(pageSize, page);
        responseHeader.set("x-total-count", String.valueOf(pageOut.getTotalElements()));
        return new ResponseEntity<List<TeacherDTO>>(RestMapper.INSTANCE.getTeacherDto(pageOut.getContent()),
                responseHeader, pageOut.getContent().size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("teachers/{id}")
    public TeacherDTO getTeacher(@PathVariable("id") Long id) {
        return RestMapper.INSTANCE.getTeacherDto(teacherService.getTeacher(id));
    }

    @PostMapping("teachers")
    public TeacherDTO addTeacher(@RequestBody Teacher teacher) {
        return RestMapper.INSTANCE.getTeacherDto(teacherService.save(teacher));
    }

    @PutMapping("teachers/{id}")
    public TeacherDTO updateTeacher(@PathVariable("id") Long id, @RequestBody Teacher teacher) {
        return RestMapper.INSTANCE.getTeacherDto(teacherService.updateTeacher(id, teacher));
    }

    @PutMapping("teachers/{id}/assign")
    public TeacherDTO assignAdvisee(@PathVariable("id") Long id, @RequestBody StudentListRequest studentListRequest) {
        List<Student> students = studentListRequest.getStudents();
        return RestMapper.INSTANCE.getTeacherDto(teacherService.assignStudent(id, students));
    }

}
