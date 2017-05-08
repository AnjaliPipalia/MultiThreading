
package multithreading;

/**
 * This is ThreadRunner class that extends thread that handles the distance covered by an animal
 * @author arp226
 */
class ThreadRunner extends Thread {

    private int number, restValue, speed;

    ThreadRunner(int number, int restValue, int speed) {
        this.number = number;
        this.restValue = restValue;
        this.speed = speed;
    }

    public void run() {
        int distance = 0;
        while (!isInterrupted() && distance < Race.DISTANCE) {
            try {
                distance = runSync(distance);
            } catch (InterruptedException e) {
                System.out.print("Thread " + number + " : You beat me fair and square." + "\n");
                break;
            }
        }
        if (distance >= Race.DISTANCE && !isInterrupted()) {
            Race.finished(Thread.currentThread(), this.number);
        }
    }

    private synchronized int runSync(int distance) throws InterruptedException {
        int rand = (int) (Math.random() * 100);
        if (restValue <= rand) {
            distance += speed;
            System.out.println("Thread " + number + " : " + distance);
        }
        Thread.sleep(100);
        return distance;
    }

}
