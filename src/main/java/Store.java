import java.util.ArrayList;
import java.util.List;

class Store {

    public static final int STOCK_BALANCE = 10;

    Seller seller = new Seller(this);
    List<Car> cars = new ArrayList<>(STOCK_BALANCE);

    public void sellCar() {

        seller.sellCar();
    }

    public void acceptCar() {

        seller.makeCar();
    }

    List<Car> getAuto() {

        return cars;
    }

}