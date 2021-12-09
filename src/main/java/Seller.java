import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Seller {

    private final Store store;
    private static final Lock LOCK = new ReentrantLock(true);
    private static final Condition CONDITION = LOCK.newCondition();

    public Seller(Store shop) {
        this.store = shop;
    }

    public void sellCar() {
        try {
            LOCK.lock();
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон.");
            while (store.getAuto().isEmpty()) {
                System.out.println(Thread.currentThread().getName() + ", извините, машин пока нет, ждем поступление.");
                CONDITION.await();
            }
            int waitingTime = 3500;
            Thread.sleep(waitingTime);
            System.out.println(Thread.currentThread().getName() + " уехал на новенькой "
                    + store.getAuto().get(0) + "! Удачи ему!");

            store.getAuto().remove(0);

        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            LOCK.unlock();
        }
        Thread.currentThread().interrupt();
    }

    public void makeCar() {
        try {
            LOCK.lock();
            while (store.getAuto().size() == 0) {
                System.out.println(Thread.currentThread().getName() + " выпустил 1 авто!");
                store.getAuto().add(new Car());
                CONDITION.signal();
            }
            int timeToMakeNewCar = 2500;
            Thread.sleep(timeToMakeNewCar);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
}