package votingsimulator;

public class Candidate implements Comparable<Candidate> {
    protected int candidateId;
    protected int voteCount;
    
    public Candidate(int id, int count) {
        candidateId = id;
        voteCount = count;
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
