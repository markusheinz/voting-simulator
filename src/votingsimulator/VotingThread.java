package votingsimulator;

import static votingsimulator.VotingSimulator.MAX_CANDIDATE;
import static votingsimulator.VotingSimulator.POINTS;

public class VotingThread extends Thread {

    protected final int count;
    protected final Candidate[] rankingMulti;
    protected final Candidate[] rankingSingle;

    public VotingThread(int start, int end) {
        count = end - start;

        if (count <= 0) {
            throw new IllegalArgumentException("end <= start");
        }

        rankingMulti =  new Candidate[MAX_CANDIDATE];
        rankingSingle = new Candidate[MAX_CANDIDATE];

        for (int i = 0; i < MAX_CANDIDATE; i++) {
            rankingMulti[i] = new Candidate(i);
            rankingSingle[i] = new Candidate(i);
        }
    }

    public Candidate[] getRankingMulti() {
        return rankingMulti;
    }

    public Candidate[] getRankingSingle() {
        return rankingSingle;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
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
