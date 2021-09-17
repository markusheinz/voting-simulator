package votingsimulator;

import static votingsimulator.VotingSimulator.MAX_CANDIDATE;
import static votingsimulator.VotingSimulator.POINTS;

public class VotingThread implements Runnable {

    protected int start;
    protected int end;
    protected Candidate[] rankingMulti;
    protected Candidate[] rankingSingle;

    public VotingThread(int start, int end, Candidate[] rankingMulti,
            Candidate[] rankingSingle) {

        this.start = start;
        this.end = end;
        this.rankingMulti = rankingMulti;
        this.rankingSingle = rankingSingle;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            Vote vote = new Vote(POINTS, MAX_CANDIDATE);
            vote.vote();

            vote.getMultiVote().entrySet().forEach(iterator -> {
                rankingMulti[iterator.getKey()].addVote(iterator.getValue());
            });

            int singleVote = vote.getSingleVote();
            rankingSingle[singleVote].addVote();
        }
    }
}
