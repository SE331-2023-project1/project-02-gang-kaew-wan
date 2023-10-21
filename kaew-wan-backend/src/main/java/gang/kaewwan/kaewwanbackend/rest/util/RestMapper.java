package gang.kaewwan.kaewwanbackend.rest.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import gang.kaewwan.kaewwanbackend.rest.entity.Announcement;
import gang.kaewwan.kaewwanbackend.rest.entity.AnnouncementDTO;
import gang.kaewwan.kaewwanbackend.rest.entity.Comment;
import gang.kaewwan.kaewwanbackend.rest.entity.CommentDTO;
import gang.kaewwan.kaewwanbackend.rest.entity.Department;
import gang.kaewwan.kaewwanbackend.rest.entity.DepartmentDTO;
import gang.kaewwan.kaewwanbackend.rest.entity.Reaction;
import gang.kaewwan.kaewwanbackend.rest.entity.ReactionDTO;
import gang.kaewwan.kaewwanbackend.rest.entity.Student;
import gang.kaewwan.kaewwanbackend.rest.entity.StudentDTO;
import gang.kaewwan.kaewwanbackend.rest.entity.StudentLightweightDTO;
import gang.kaewwan.kaewwanbackend.rest.entity.Teacher;
import gang.kaewwan.kaewwanbackend.rest.entity.TeacherDTO;

@Mapper
public interface RestMapper {
    RestMapper INSTANCE = Mappers.getMapper(RestMapper.class);

    StudentDTO getStudentDto(Student student);

    List<StudentDTO> getStudentDto(List<Student> students);

    StudentLightweightDTO getStudentLightweightDTO(Student student);

    List<StudentLightweightDTO> getStudentLightweightDTO(List<Student> student);

    CommentDTO getCommentDto(Comment comment);

    List<CommentDTO> getCommentDto(List<Comment> comments);

    TeacherDTO getTeacherDto(Teacher teacher);

    List<TeacherDTO> getTeacherDto(List<Teacher> teachers);

    AnnouncementDTO getAnnouncementDTO(Announcement announcement);

    List<AnnouncementDTO> getAnnouncementDTO(List<Announcement> announcements);

    List<ReactionDTO> getReactionDTO(List<Reaction> reactions);

    ReactionDTO getReactionDTO(Reaction reaction);

    List<DepartmentDTO> getDepartmentDTO(List<Department> departments);

    DepartmentDTO getDepartmentDTO(Department department);

}
