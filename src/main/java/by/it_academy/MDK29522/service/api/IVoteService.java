package by.it_academy.MDK29522.service.api;

import by.it_academy.MDK29522.core.dto.VoteDTO;
import by.it_academy.MDK29522.dao.entity.Vote;

import java.util.List;

public interface IVoteService {
    List<Vote> getVotes();
    void saveVote(VoteDTO voteDTO);
}
