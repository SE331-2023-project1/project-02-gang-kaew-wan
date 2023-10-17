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
public class Teacher extends Person {
    String position;

    @OneToMany(mappedBy = "teacher")
    List<Annoucement> annoucements;

    @ManyToOne
    Department department;

    @OneToMany(mappedBy = "teacher")
    List<Review> reviews;

    @OneToMany(mappedBy = "teacher")
    List<Student> students;

    @OneToMany(mappedBy = "teacher")
    List<Comment> comments;
}
