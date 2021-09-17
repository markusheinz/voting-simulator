package votingsimulator;

import java.util.concurrent.atomic.AtomicInteger;

public class Candidate implements Comparable<Candidate> {
    protected int candidateId;
    protected AtomicInteger voteCount;
    
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
