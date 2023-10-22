package gang.kaewwan.kaewwanbackend.rest.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.transaction.Transactional;
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

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    List<Announcement> announcements;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    List<Review> reviews;

    @OneToMany(mappedBy = "teacher")
    List<Student> students;

    @PreRemove
    @Transactional
    private void preRemove() {
        students.forEach(student -> student.setTeacher(null));
    }
}
