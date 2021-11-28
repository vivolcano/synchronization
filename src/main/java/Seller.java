class Seller {

    private final Store store;

    public Seller(Store shop) {
        this.store = shop;
    }

    public synchronized void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон.");
            while (store.getAuto().isEmpty()) {
                System.out.println(Thread.currentThread().getName() + ", извините, машин пока нет, ждем поступление.");
                wait();
            }
            int waitingTime = 3500;
            Thread.sleep(waitingTime);
            System.out.println(Thread.currentThread().getName() + " уехал на новенькой машине, удачи!"
                    + store.getAuto().get(0));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        store.getAuto().remove(0);
        notifyAll();
    }

    public synchronized void makeCar() {
        try {
            while (store.getAuto().isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " выпустил 1 авто!");
                store.getAuto().add(new Car());
                notify();
                wait();
            }
            int timeToMakeNewCar = 2500;
            Thread.sleep(timeToMakeNewCar);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}