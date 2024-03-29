package by.it_academy.MDK29522.util.comparator;

import by.it_academy.MDK29522.dao.entity.Vote;

import java.util.Comparator;

public class ComparatorVoteStatistic implements Comparator<Vote> {
    @Override
    public int compare(Vote o1, Vote o2) {
        return o1.getDateCreate().compareTo(o2.getDateCreate());
    }
}
