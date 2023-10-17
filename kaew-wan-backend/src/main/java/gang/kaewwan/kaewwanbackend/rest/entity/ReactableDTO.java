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
public class ReactableDTO {
    Long id;
    List<ReactionDTO> reactions;
}
