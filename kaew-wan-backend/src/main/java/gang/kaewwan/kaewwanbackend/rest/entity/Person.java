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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    User user;

    String fname;
    String lname;
    String image;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> sendComments;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> receivedComments;
}
