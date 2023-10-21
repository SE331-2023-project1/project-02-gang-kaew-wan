package gang.kaewwan.kaewwanbackend.rest.entity;

import gang.kaewwan.kaewwanbackend.security.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    @ManyToOne
    Department department;

    @OneToOne
    User user;

    String fname;
    String lname;
    String image;

    @OneToMany(mappedBy = "sender")
    List<Comment> sendComments;

    @OneToMany(mappedBy = "receiver" )
    List<Comment> receivedComments;
}
