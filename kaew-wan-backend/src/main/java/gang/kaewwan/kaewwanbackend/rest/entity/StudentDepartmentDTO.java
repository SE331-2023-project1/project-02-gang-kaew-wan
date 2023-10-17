package gang.kaewwan.kaewwanbackend.rest.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDepartmentDTO {
    Long id;
    String name;
    List<DepartmentDTO> teachers;
}
