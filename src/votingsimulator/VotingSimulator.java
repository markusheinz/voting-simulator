package votingsimulator;

import java.util.Arrays;
import java.util.Map.Entry;

public class VotingSimulator {
    public static final int MAX_CANDIDATE = 30;
    public static final int[] POINTS = {5, 4, 3};    
    public static int VOTERS = 60000000;
    
    public static void main(String[] args) {
        doElection();
    }
    
    protected static void doElection() {
        Vote[] votes = new Vote[VOTERS];
        
        int[] rankingMulti = new int[MAX_CANDIDATE];
        int[] rankingSingle = new int [MAX_CANDIDATE];
        
        for (int i = 0; i < MAX_CANDIDATE; i++) {
            rankingMulti[i] = 0;
            rankingSingle[i] = 0;
        }
        
        for (Vote vote : votes) {
            vote = new Vote(POINTS, MAX_CANDIDATE);
            vote.vote();
            
            for (Entry<Integer, Integer> iterator : 
                    vote.getMultiVote().entrySet()) {
                
                rankingMulti[iterator.getKey()] += iterator.getValue();
            }
            
            
            int singleVote = vote.getSingleVote();
            rankingSingle[singleVote]++;
        }
        
        printRanking(rankingSingle);
        printRanking(rankingMulti);
    }
    
    protected static void printRanking(int [] ranking) {
        Candidate[] candidates = new Candidate[MAX_CANDIDATE];
        
        for (int i = 0; i < MAX_CANDIDATE; i++) {
            candidates[i] = new Candidate(i, ranking[i]);
        }
        
        Arrays.sort(candidates);
        
        for (int i = 0; i < MAX_CANDIDATE; i++) {
            System.out.println(candidates[i].toString());
        }
        
        System.out.println();
    }
}
