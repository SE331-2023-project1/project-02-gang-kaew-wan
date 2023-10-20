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

    List<CommentDTO> getCommentDto(List<Comment> comments);
    CommentDTO getCommentDto(Comment comment);
    TeacherDTO getTeacherDto(Teacher teacher);

    List<TeacherDTO> getTeacherDto(List<Teacher> teachers);

    List<AnnouncementDTO> getAnnouncementDTO(List<Announcement> announcements);
    AnnouncementDTO getAnnouncementDTO(Announcement announcement);

}
