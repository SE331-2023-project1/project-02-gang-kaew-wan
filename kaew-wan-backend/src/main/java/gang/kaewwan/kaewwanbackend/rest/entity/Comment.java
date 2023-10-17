package gang.kaewwan.kaewwanbackend.rest.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Comment extends Reactable {

    String message;
    Boolean edited;

    @ManyToOne
    Comment parent;

    @OneToMany
    List<Comment> children;

    @ManyToOne
    Student student;

    @ManyToOne
    Teacher teacher;
}
