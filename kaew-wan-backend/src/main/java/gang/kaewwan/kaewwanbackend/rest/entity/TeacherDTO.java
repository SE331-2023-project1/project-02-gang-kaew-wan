package gang.kaewwan.kaewwanbackend.rest.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeacherDTO extends PersonDTO {
    String position;

    List<AnnouncementDTO> announcements;

    DepartmentDTO department;

    List<ReviewDTO> reviews;

    List<TeacherStudentDTO> students;

    List<TeacherCommentDTO> comments;
}
