package gang.kaewwan.kaewwanbackend.rest.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class Comment extends Reactable {

    @Column(columnDefinition = "TEXT")
    String message;
    Boolean edited;

    @ManyToOne
    Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    List<Comment> children;

    @ManyToOne
    Person sender;

    @ManyToOne
    Person receiver;
}
