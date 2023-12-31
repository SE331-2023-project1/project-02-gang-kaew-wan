package gang.kaewwan.kaewwanbackend.rest.entity;

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
public class TeacherCommentDTO extends ReactableDTO {
    String message;
    Boolean edited;

    CommentStrippedDTO parent;

    StudentStrippedDTO student;
}
