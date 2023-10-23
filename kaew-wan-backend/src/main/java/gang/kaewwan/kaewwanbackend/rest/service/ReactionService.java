package gang.kaewwan.kaewwanbackend.rest.service;

import gang.kaewwan.kaewwanbackend.rest.entity.Reaction;
import org.springframework.data.domain.Page;

public interface ReactionService {
    Page<Reaction> getReactions();
    Reaction getReactionById(Long id);
    Reaction addReactionToReactableItem(Long id ,Reaction reaction);
    Reaction deleteReaction(Long id);
}
