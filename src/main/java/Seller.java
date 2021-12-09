class Seller {

    private final Store store;

    public Seller(Store shop) {
        this.store = shop;
    }

    public synchronized Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон.");
            while (store.getAuto().size() == 0) {
                System.out.println(Thread.currentThread().getName()
                        + ", извините, машин пока нет, ждем поступление.");
                wait();
            }
            int waitingTime = 1500;
            Thread.sleep(waitingTime);
            System.out.println(Thread.currentThread().getName() + " уехал на новенькой машине "
                    + store.getAuto().get(0) + "! Удачи ему!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return store.getAuto().remove(0);
    }

    public synchronized void makeCar() {
        try {
            while (store.getAuto().size() == 0) {
                System.out.println("[ПРОЗВОДСТВО]");
                int timeToMakeNewCar = 2000;
                Thread.sleep(timeToMakeNewCar);
                store.getAuto().add(new Car());
                System.out.println(Thread.currentThread().getName() + " выпустил 1 авто!");
                notify();
                int productionBreak = 1500;
                wait(productionBreak);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}