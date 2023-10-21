package gang.kaewwan.kaewwanbackend.rest.util;

import java.util.List;

import gang.kaewwan.kaewwanbackend.rest.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestMapper {
    RestMapper INSTANCE = Mappers.getMapper(RestMapper.class);

    StudentDTO getStudentDto(Student student);

    List<StudentDTO> getStudentDto(List<Student> students);

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
