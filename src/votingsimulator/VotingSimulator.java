package votingsimulator;

import java.util.Arrays;

public class VotingSimulator {
    public static final int MAX_CANDIDATE = 30;
    public static final int[] POINTS = {5, 4, 3};    
    public static final int VOTERS = 60000000;
    public static final int THREAD_COUNT = 4;

    protected static final VotingThread[] workers =
            new VotingThread[THREAD_COUNT];
    
    public static void main(String[] args) {
        runThreads();
        calculateResults();
    }
    
    protected static void runThreads() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            int start = i * (VOTERS / THREAD_COUNT);
            int end  = (i + 1) * (VOTERS / THREAD_COUNT);
            
            workers[i] = new VotingThread(start, end);
            workers[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            try {
                workers[i].join();
            } catch (InterruptedException ex) {
                // do nothing
            }
        }
    }

    protected static void calculateResults() {
        Candidate[] rankingMulti =  new Candidate[MAX_CANDIDATE];
        Candidate[] rankingSingle = new Candidate[MAX_CANDIDATE];

        for (int i = 0; i < MAX_CANDIDATE; i++) {
            rankingMulti[i] = new Candidate(i);
            rankingSingle[i] = new Candidate(i);

            for (int j = 0; j < THREAD_COUNT; j++) {
                int multi = workers[j].getRankingMulti()[i].getVoteCount();
                rankingMulti[i].addVote(multi);

                int single = workers[j].getRankingSingle()[i].getVoteCount();
                rankingSingle[i].addVote(single);
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
