package gang.kaewwan.kaewwanbackend.rest.entity;

import java.util.List;

import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public abstract class Reactable {
    @OneToMany(mappedBy = "reaction")
    List<Reaction> reactions;
}
