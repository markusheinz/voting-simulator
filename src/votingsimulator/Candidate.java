package votingsimulator;

import java.util.concurrent.atomic.AtomicInteger;

public class Candidate implements Comparable<Candidate> {
    protected final int candidateId;
    protected final AtomicInteger voteCount;
    
    public Candidate(int id) {
        candidateId = id;
        voteCount = new AtomicInteger();
    }

    public void addVote() {
        addVote(1);
    }

    public void addVote(int points) {
        voteCount.getAndAdd(points);
    }

    public int getVoteCount() {
        return voteCount.get();
    }

    @Override
    public int compareTo(Candidate o) {
        return o.voteCount.get() - voteCount.get();
    }
    
    @Override
    public String toString() {
        return String.format("Candidate %02d has %10d votes.", 
                candidateId, voteCount.get());
    }
            
}
