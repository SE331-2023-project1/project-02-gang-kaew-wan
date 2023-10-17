package gang.kaewwan.kaewwanbackend.rest.util;

import java.util.List;

import gang.kaewwan.kaewwanbackend.rest.entity.Comment;
import gang.kaewwan.kaewwanbackend.rest.entity.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import gang.kaewwan.kaewwanbackend.rest.entity.Student;
import gang.kaewwan.kaewwanbackend.rest.entity.StudentDTO;
import gang.kaewwan.kaewwanbackend.rest.entity.Teacher;
import gang.kaewwan.kaewwanbackend.rest.entity.TeacherDTO;

@Mapper
public interface RestMapper {
    RestMapper INSTANCE = Mappers.getMapper(RestMapper.class);

    StudentDTO getStudentDto(Student student);

    List<StudentDTO> getStudentDto(List<Student> students);

    List<CommentDTO> getCommentDto(List<Comment> comments);
    CommentDTO getCommentDto(Comment comment);
    TeacherDTO getTeacherDto(Teacher teacher);

    List<TeacherDTO> getTeacherDto(List<Teacher> teachers);

}
