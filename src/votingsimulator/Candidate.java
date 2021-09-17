package votingsimulator;

public class Candidate implements Comparable<Candidate> {
    protected final int candidateId;
    protected int voteCount;
    
    public Candidate(int id) {
        candidateId = id;
        voteCount = 0;
    }

    public void addVote() {
        addVote(1);
    }

    public void addVote(int points) {
        voteCount += points;
    }

    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public int compareTo(Candidate o) {
        return o.voteCount - voteCount;
    }
    
    @Override
    public String toString() {
        return String.format("Candidate %02d has %10d votes.", 
                candidateId, voteCount);
    }
            
}
