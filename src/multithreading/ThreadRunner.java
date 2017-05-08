
package multithreading;

/**
 * This is ThreadRunner class that extends thread that handles the distance covered by an animal
 * @author arp226
 */
class ThreadRunner extends Thread {

    private int number, restValue, baseSpeed, clumsiness, tripCount = 0;

    ThreadRunner(int number, int restValue, int speed, int clumsiness) {
        this.number = number;
        this.restValue = restValue;
        this.baseSpeed = speed;
        this.clumsiness = clumsiness;
    }

    public void run() {
        int distance = 0;
        int speed = baseSpeed;
        while (!isInterrupted() && distance < Race.DISTANCE) {
            try {
                int rand = (int) (Math.random() * 100);
                if (tripCount == 0) {
                    if (clumsiness <= rand) {
                        speed = baseSpeed / 2;
                        System.out.println("Thread " + number + " tripped. Speed reduced to " + speed);
                        tripCount = 5;
                    }
                } else {
                    tripCount--;
                    if (tripCount == 0) {
                        speed = baseSpeed;
                    }
                }
                if (restValue <= rand) {
                    distance += speed;
                    System.out.println("Thread " + number + " : " + distance);
                }
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.out.print("Thread " + number + " : You beat me fair and square." + "\n");
                break;
            }
        }
        if (distance >= Race.DISTANCE && !isInterrupted()) {
            Race.finished(Thread.currentThread(), this.number);
        }
    }

}
