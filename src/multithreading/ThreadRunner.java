
package multithreading;

/**
 * This is ThreadRunner class that extends thread that handles the distance covered by an animal
 * @author arp226
 */
class ThreadRunner extends Thread {

    private String name;
    private int restValue, speed;

    ThreadRunner(String name, int restValue, int speed) {
        this.name = name;
        this.restValue = restValue;
        this.speed = speed;
    }

    public void run() {
        int distance = 0;
        while (!isInterrupted() && distance < 1000) {
            try {
                int rand = (int) (Math.random() * 100);
                if (restValue <= rand) {
                    distance += speed;
                    System.out.println(name + " : " + distance);
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(name + ": You beat me fair and square." + "\n");
                break;
            }
        }
        if (distance >= 1000) {
            System.out.println(name + ": I finished!" + "\n");
            Race.finished(Thread.currentThread(), name);
        }
    }

}
