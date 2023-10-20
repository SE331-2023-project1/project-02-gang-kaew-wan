package gang.kaewwan.kaewwanbackend.rest.controller;

import gang.kaewwan.kaewwanbackend.rest.entity.Reaction;
import gang.kaewwan.kaewwanbackend.rest.entity.ReactionDTO;
import gang.kaewwan.kaewwanbackend.rest.service.ReactionService;
import gang.kaewwan.kaewwanbackend.rest.util.RestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReactionController {
    final ReactionService reactionService;
    @GetMapping("reactions")
    public List<ReactionDTO> getReactions() {
        Page<Reaction> result =  reactionService.getReactions();
        return RestMapper.INSTANCE.getReactionDTO(result.getContent());
    }

    @PostMapping("reactions/{reactableId}")
    public ReactionDTO addReactionToReactable(@PathVariable("reactableId") Long reactableId, @RequestBody Reaction reaction){
        Reaction result = reactionService.addReactionToReactableItem(reactableId, reaction);
        return RestMapper.INSTANCE.getReactionDTO(result);
    }

    @DeleteMapping("reactions/{reactionId}")
    public ReactionDTO deleteReaction(@PathVariable("reactionId") Long reactionId){
        Reaction result = reactionService.deleteReaction(reactionId);
        return RestMapper.INSTANCE.getReactionDTO(result);
    }
}
