package multithreading;

/**
 * This is a Race class that initiates the race
 * @author arp226
 */
public class Race {
	public static Thread tortoise;
	public static Thread hare;

	public static void main(String[] args) {

		tortoise = new ThreadRunner("Tortoise", 0, 10);
		hare = new ThreadRunner("Hare", 90, 100);

		System.out.println("Get set... Go!");
		tortoise.start();
		hare.start();

	}

	public static synchronized void finished(Thread winner, String winnerName) {

		if (winner.equals(hare)) {
			System.out.println("The race is over! The Hare is the winner" + "\n");
			tortoise.interrupt();
		}
		if (winner.equals(tortoise)) {
			System.out.println("The race is over! The Tortoise is the winner" + "\n");
			hare.interrupt();
		}
	}

}
