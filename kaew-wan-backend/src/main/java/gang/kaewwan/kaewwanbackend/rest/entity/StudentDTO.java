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
public class StudentDTO extends PersonDTO {
    String studentId;
    List<ReviewDTO> reviews;
    StudentTeacherDTO teacher;
}
