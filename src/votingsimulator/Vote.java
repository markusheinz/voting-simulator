package votingsimulator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Vote {
    protected int[] voteOptions;
    protected int maxCandidates;
    
    protected Map<Integer, Integer> multiVote;
    protected int singleVote;
    
    protected static final Random random = new Random();
    
    public Vote(int[] voteOptions, int maxCandidates) {
        this.voteOptions = voteOptions;
        this.maxCandidates = maxCandidates;
        
        multiVote = new HashMap<>();
        singleVote = -1;
    }
    
    public void vote() {
        boolean first = true;
        
        for (int points : voteOptions) {
            boolean found = false;
            int candidate = -1;
            
            do {
                candidate = random.nextInt(maxCandidates);
                
                if (!multiVote.containsKey(candidate)) {
                    multiVote.put(candidate, points);    
                    found = true;
                }
            } while (!found);
            
            if (first) {
               singleVote = candidate;
               first = false;         
            }
        }
    }
    
    public Map<Integer, Integer> getMultiVote() {
        return multiVote;
    }
    
    public int getSingleVote() {
        return singleVote;
    }
}
