package multithreading;

/**
 * This is a Race class that initiates the race
 * @author arp226
 */
public class Race {
	static Thread tortoise;
	static Thread hare;
    static int winnerHare = 0;
    static int winnerTortoise = 0;
    final int RACES = 3;

	public static void main(String[] args) {
        new Race().start();
    }

    private void start() {
        int raceNumber = 0;
        while (raceNumber < RACES) {
            tortoise = new ThreadRunner("Tortoise", 0, 10);
            hare = new ThreadRunner("Hare", 90, 100);
            if (!tortoise.isAlive() && !hare.isAlive()) {
                System.out.println("Get set... Go!");
                tortoise.start();
                hare.start();
            }
            while(tortoise.isAlive() && hare.isAlive()) {
            }
            raceNumber++;
        }

        System.out.println("T: " + winnerTortoise + " | H: " + winnerHare);
    }

    static synchronized void finished(Thread winner, String winnerName) {

		if (winner.equals(hare)) {
			System.out.println("The race is over! The Hare is the winner" + "\n");
            winnerHare++;
        }
        if (winner.equals(tortoise)) {
            System.out.println("The race is over! The Tortoise is the winner" + "\n");
            winnerTortoise++;
        }

        tortoise.interrupt();
        hare.interrupt();
    }

}
