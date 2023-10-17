package gang.kaewwan.kaewwanbackend.rest.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Student extends Person {
    String studentId;

    @ManyToOne
    Department department;

    @OneToMany(mappedBy = "student")
    List<Review> reviews;

    @ManyToOne
    Teacher teacher;

    @OneToMany(mappedBy = "student")
    List<Comment> comments;
}
