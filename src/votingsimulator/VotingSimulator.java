package votingsimulator;

import java.util.Arrays;

public class VotingSimulator {
    public static final int MAX_CANDIDATE = 30;
    public static final int[] POINTS = {5, 4, 3};    
    public static final int VOTERS = 60000000;
    public static final int THREAD_COUNT = 4;

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

        final Thread[] workers = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            int start = i * (VOTERS / THREAD_COUNT);
            int end  = (i + 1) * (VOTERS / THREAD_COUNT);
            
            workers[i] = new Thread(new VotingThread(start, end, rankingMulti, 
                    rankingSingle));
            workers[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            try {
                workers[i].join();
            } catch (InterruptedException ex) {
                // do nothing
            }
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
