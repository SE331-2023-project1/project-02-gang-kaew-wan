package gang.kaewwan.kaewwanbackend.rest.service;

import gang.kaewwan.kaewwanbackend.rest.dao.ReactionDao;
import gang.kaewwan.kaewwanbackend.rest.entity.Reaction;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService{
    final ReactionDao reactionDao;

    @Override
    public Page<Reaction> getReactions() {
        return this.reactionDao.getReactions();
    }

    @Override
    public Reaction getReactionById(Long id) {
        return this.reactionDao.getReactionById(id);
    }

    @Override
    public Reaction addReactionToReactableItem(Long id, Reaction reaction) {
        return this.reactionDao.addReactionToReactableItem(id, reaction);
    }

    @Override
    public Reaction deleteReaction(Long id) {
        return this.reactionDao.deleteReaction(id);
    }
}
