package multithreading;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is a Race class that initiates the race
 *
 * @author arp226
 */
public class Race {
    private static ExecutorService executor;
    private final int RACES = 100;
    private static final int RUNNERS = 9;
    protected static int DISTANCE = 1000;
    private static int[] times = new int[RUNNERS];

    public static void main(String[] args) {
        new Race().start();
    }

    private void start() {
        int raceNumber = 0;
        while (raceNumber < RACES) {
            executor = Executors.newFixedThreadPool(RUNNERS);

            System.out.println("Get set... Go!");
            int rest = 0;
            for (int i = 0; i < RUNNERS; i++) {
                Thread runner = new ThreadRunner(i+1, rest, rest+10, (int) (Math.random() * 100));
                rest += 10;
                executor.execute(runner);
            }
            while (!executor.isTerminated() || !executor.isShutdown()) {
            }
            raceNumber++;
        }

        System.out.println("Winners are");
        for (int i = 0; i < RUNNERS; i++) {
            System.out.println("Thread " + (i+1) + ": " + times[i]);
        }
    }

    static synchronized void finished(Thread winner, int index) {
        executor.shutdownNow();
        times[index-1] = ++times[index-1];
        System.out.println("Thread " + index + ": I finished!" + "\n");
    }

}
