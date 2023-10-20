package gang.kaewwan.kaewwanbackend.rest.dao;

import gang.kaewwan.kaewwanbackend.rest.entity.Reactable;
import gang.kaewwan.kaewwanbackend.rest.entity.Reaction;
import gang.kaewwan.kaewwanbackend.rest.repository.AnnouncementRepository;
import gang.kaewwan.kaewwanbackend.rest.repository.CommentRepository;
import gang.kaewwan.kaewwanbackend.rest.repository.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class ReactionDaoImpl implements ReactionDao {
    final ReactionRepository reactionRepository;
    final CommentRepository commentRepository;
    final AnnouncementRepository announcementRepository;

    @Override
    public Page<Reaction> getReactions() {
        return reactionRepository.findAll(Pageable.unpaged());
    }

    @Override
    public Reaction getReactionById(Long id) {
        return reactionRepository.findById(id).orElse(null);
    }

    @Override
    public Reaction addReactionToReactableItem(Long id, Reaction reaction) {
        Reactable reactable;
        if (commentRepository.existsById(id)) {
            reactable = commentRepository.findById(id).orElse(null);
            reaction.setReactable(reactable);
            reactionRepository.save(reaction);
            reactable.getReactions().add(reaction);

        } else if (announcementRepository.existsById(id)) {
            reactable = announcementRepository.findById(id).orElse(null);
            reaction.setReactable(reactable);
            reactionRepository.save(reaction);
            reactable.getReactions().add(reaction);

        } else {
            return null;
        }
        return reaction;
    }

    @Override
    public Reaction deleteReaction(Long id) {
        reactionRepository.deleteById(id);
        return null;
    }
}
