package votingsimulator;

import java.util.Arrays;
import java.util.Map.Entry;

public class VotingSimulator {
    public static final int MAX_CANDIDATE = 30;
    public static final int[] POINTS = {5, 4, 3};    
    public static final int VOTERS = 60000000;

    protected static final Vote[] votes = new Vote[VOTERS];

    protected static final Candidate[] rankingMulti =
            new Candidate[MAX_CANDIDATE];

    protected static final Candidate[] rankingSingle =
            new Candidate[MAX_CANDIDATE];
    
    public static void main(String[] args) {
        doElection();
    }
    
    protected static void doElection() {
        for (int i = 0; i < MAX_CANDIDATE; i++) {
            rankingMulti[i] = new Candidate(i);
            rankingSingle[i] = new Candidate(i);
        }
        
        for (Vote vote : votes) {
            vote = new Vote(POINTS, MAX_CANDIDATE);
            vote.vote();
            
            for (Entry<Integer, Integer> iterator : 
                    vote.getMultiVote().entrySet()) {
                
                rankingMulti[iterator.getKey()].addVote(iterator.getValue());
            }
            
            int singleVote = vote.getSingleVote();
            rankingSingle[singleVote].addVote();
        }
        
        printRanking("Single Vote", rankingSingle);
        printRanking("Multi Vote", rankingMulti);
    }
    
    protected static void printRanking(String label, Candidate[] ranking) {
        System.out.println(label);
        
        int sum = 0;
        Arrays.sort(ranking);

        for (int i = 0; i < MAX_CANDIDATE; i++) {
            System.out.println(ranking[i].toString());
            sum += ranking[i].getVoteCount();
        }
        
        System.out.println(String.format("Sum: %22d votes", sum));
        System.out.println();
    }
}
