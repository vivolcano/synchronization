public class Main {
    public static void main(String[] args) throws InterruptedException {

        final Store STORE = new Store();
        final int BUYERS = 5;

        for (int i = 1; i <= BUYERS; i++) {
            new Thread(null, STORE::sellCar, "Покупатель " + i).start();
            int delayedBuyerVisit = 1000;
            Thread.sleep(delayedBuyerVisit);
        }

        new Thread(null, STORE::acceptCar, "Завод Лады").start();

    }
}