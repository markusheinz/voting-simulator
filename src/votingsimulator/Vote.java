package votingsimulator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Vote {
    protected int[] voteOptions;
    protected int maxCandidates;
    
    protected Set<Integer> candidatesChosen;
    
    protected Map<Integer, Integer> multiVote;
    protected int singleVote;
    
    protected static final Random random = new Random();
    
    public Vote(int[] voteOptions, int maxCandidates) {
        this.voteOptions = voteOptions;
        this.maxCandidates = maxCandidates;
        
        candidatesChosen = new HashSet<>();
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
                
                if (!candidatesChosen.contains(candidate)) {
                    candidatesChosen.add(candidate);
                    found = true;
                }
            } while (!found);
            
            multiVote.put(candidate, points);
            
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
