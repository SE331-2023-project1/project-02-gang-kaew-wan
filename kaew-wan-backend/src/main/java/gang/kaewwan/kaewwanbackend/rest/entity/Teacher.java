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
public class Teacher extends Person {
    String position;

    @OneToMany
    List<Annoucement> annoucements;

    @ManyToOne
    Department department;

    @OneToMany
    List<Review> reviews;
}
